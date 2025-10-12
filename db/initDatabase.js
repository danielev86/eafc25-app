const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('eafc_issues.db');

// --- Player Issues ---
const playerIssueList = [
  { id: "1", issue_type: "RAFFREDDORE", issue_description: "3 GIORNI FUORI PER INFORTUNEO" },
  { id: "3", issue_type: "INFLUENZA", issue_description: "7 GIORNI FUORI PER INFORTUNEO" },
  { id: "5", issue_type: "VIRUS", issue_description: "14 GIORNI FUORI PER INFORTUNEO" },
  { id: "7", issue_type: "COVID-19", issue_description: "21 GIORNI FUORI PER INFORTUNEO" },
  { id: "9", issue_type: "GRAVE INFEZIONE VIRALE", issue_description: "1 MESE FUORI PER INFORTUNEO" },
  { id: "11", issue_type: "CAVIGLIA SLOGATA", issue_description: "20 GIORNI FUORI PER INFORTUNEO" },
  { id: "13", issue_type: "STIRAMENTO ALLA CAVIGLIA", issue_description: "14 GIORNI FUORI PER INFORTUNEO" },
  { id: "15", issue_type: "CONTUSIONE AL PIEDE", issue_description: "15 GIORNI FUORI PER INFORTUNEO" },
  { id: "17", issue_type: "STIRAMENTO GINOCCHIO", issue_description: "10 GIORNI FUORI PER INFORTUNEO" },
  { id: "19", issue_type: "DISTORSIONE GINOCCHIO", issue_description: "30 GIORNI FUORI PER INFORTUNEO" },
  { id: "21", issue_type: "INFIAMMAZIONE ROTULA", issue_description: "20 GIORNI FUORI PER INFORTUNEO" },
  { id: "23", issue_type: "STRAPPO LEGAMENTO GINOCCHIO", issue_description: "60 GIORNI FUORI PER INFORTUNEO" },
  { id: "25", issue_type: "STIRAMENTO BICIPITE FEMORALE", issue_description: "7 GIORNI FUORI PER INFORTUNEO" },
  { id: "27", issue_type: "CONTUSIONE COSCIA", issue_description: "5 GIORNI FUORI PER INFORTUNEO" },
  { id: "29", issue_type: "STIRAMENTO MUSCOLO COSCIA", issue_description: "14 GIORNI FUORI PER INFORTUNEO" },
  { id: "31", issue_type: "STIRAMENTO INGUINE", issue_description: "14 GIORNI FUORI PER INFORTUNEO" },
  { id: "33", issue_type: "STRAPPO MUSCOLO INGUINE", issue_description: "25 GIORNI FUORI PER INFORTUNEO" },
  { id: "35", issue_type: "CONTUSIONE COSTOLA", issue_description: "3 GIORNI FUORI PER INFORTUNEO" },
  { id: "37", issue_type: "STIRAMENTO ALLA SCHIENA", issue_description: "14 GIORNI FUORI PER INFORTUNEO" },
  { id: "39", issue_type: "SCANDALO SOCIAL", issue_description: "15 GIORNI PER COMPORTAMENTO SCORRETTO" },
  { id: "41", issue_type: "CREDO IN TE", issue_description: "SCHIERA TITOLARE UNO DEI PEGGIORI GIOCATORI DELLA ROSA" },
  { id: "43", issue_type: "RITARDO ALLENAMENTO", issue_description: "7 GIORNI FUORI PER COMPORTAMENTO NON PROFESSIONALE" },
  { id: "45", issue_type: "BERE AL PARCO", issue_description: "LICENZIA IL GIOCATORE PIU' FORTE E I DUE PIU' DEBOLI DEL VIVAIO" },
  { id: "47", issue_type: "QUANDO TOCCA A NOI", issue_description: "SCHIERA LE RISERVE PER LA PROSSIMA PARTITA" },
  { id: "49", issue_type: "SQUALIFICA PER INGIURIA ARBITRO", issue_description: "IL GIOCATORE E' SQUALIFICATO PER 2 PARTITE PER OFFESE ALL'ARBITRO" }
];

// --- Team Issues ---
const teamIssueList = [
  { id: "1", issue_type: "DEBITO SOCIETA 10%", issue_description: "RIDUZIONE BUDGET 10%" },
  { id: "2", issue_type: "DEBITO SOCIETA 30%", issue_description: "RIDUZIONE BUDGET 30%" },
  { id: "3", issue_type: "DEBITO SOCIETA 50%", issue_description: "RIDUZIONE BUDGET 50%" },
  { id: "4", issue_type: "DEBITO SOCIETA 70%", issue_description: "RIDUZIONE BUDGET 70%" },
  { id: "6", issue_type: "DEBITO SOCIETA 100%", issue_description: "BANCAROTTA" },
  { id: "7", issue_type: "BLOCCO MERCATO", issue_description: "BLOCCO MERCATO" },
  { id: "8", issue_type: "CAMBIO SOCIETA AUMENTO 10%", issue_description: "AUMENTO BUDGET 10%" },
  { id: "9", issue_type: "CAMBIO SOCIETA AUMENTO 30%", issue_description: "AUMENTO BUDGET 30%" },
  { id: "11", issue_type: "CAMBIO SOCIETA AUMENTO 50%", issue_description: "AUMENTO BUDGET 50%" },
  { id: "12", issue_type: "CAMBIO SOCIETA AUMENTO 70%", issue_description: "AUMENTO BUDGET 70%" },
  { id: "13", issue_type: "CAMBIO SOCIETARIO FONDO ARABO/CINESE", issue_description: "AUMENTO BUDGET 250 MILIONI EURO" },
  { id: "14", issue_type: "PRESIDENTE PATRIOTTICO", issue_description: "SCHIERARE IN CAMPO ALMENO 6 GIOCATORI DELLA PROPRIA NAZIONE DI APPARTENENZA" },
  { id: "16", issue_type: "COSTRUZIONE NUOVO STADIO", issue_description: "RIDUZIONE 50% DEL BUDGET TRASFERIMENTI PER COSTUZIONE NUOVO STADIO" },
  { id: "17", issue_type: "RISOLUZIONE DI UN CONTRATTO CON UNO SPONSOR IMPORTANTE", issue_description: "RIDUZIONE DEI RICAVI ANNUALI; NECESSITÀ DI CERCARE NUOVI SPONSOR IN TEMPI BREVI O VENDITA DEI DUE GIOCATORI PIU FORTI PER IL MERCATO" },
  { id: "18", issue_type: "DECLINO IMPROVVISO DEI RICAVI DA BIGLIETTI", issue_description: "I FONDI PER IL CLUB DIVENTANO PIÙ RISTRETTI, CON UN IMPATTO NEGATIVO SULLE OPERAZIONI QUOTIDIANE, INCLUSI STIPENDI E TRASFERIMENTI." },
  { id: "19", issue_type: "DIMINUZIONE DEI RICAVI DA DIRITTI TV", issue_description: "RIDUZIONE DEI RICAVI A LUNGO TERMINE, CON IL CLUB COSTRETTO A TAGLIARE ALTRE SPESE, INCLUSI GLI STIPENDI DEI GIOCATORI O GLI INVESTIMENTI IN NUOVE STRUTTURE. VENDI ALMENO 5 GIOCATORI" },
  { id: "20", issue_type: "PROBLEMI FISCALI CON IL FISCO LOCALE", issue_description: "MULTA SOSTANZIOSA E POSSIBILE RIDUZIONE DELLE RISORSE DISPONIBILI PER ACQUISTI O INVESTIMENTI. VENDI PRIMA DI COMPRARE GIOCATORI CON ALMENO PAREGGIO DI BILANCIO O FINANZE IN ATTIVO" },
  { id: "21", issue_type: "FALLIMENTO DI UN INVESTIMENTO IMMOBILIARE", issue_description: "AUMENTO DEL DEBITO O NECESSITÀ DI RISTRUTTURARE L'INIZIATIVA PER RECUPERARE LE PERDITE. VENDI 10 GIOCATORI" }
];

// --- Market Issues ---
const marketIssueList = [
  { id: "1", issue_type: "Nessun imprevisto", issue_description: "Nessun imprevisto!" },
  { id: "2", issue_type: "Arrivano gli americani", issue_description: "Compra giocatori di prospettiva per far plusvlenze" },
  { id: "3", issue_type: "Arrivano gli sceicchi", issue_description: "Spendi tutto il budget per vincere un trofeo" },
  { id: "4", issue_type: "Presidente patriottico", issue_description: "Schierare in campo almeno 6 giocatori della propria nazione di appartenenza" },
  { id: "5", issue_type: "Operazione rifondazione", issue_description: "Rifondare la squadra per imposizione societaria" },
  { id: "7", issue_type: "Operazione giovanili", issue_description: "Schierare nella formazione almeno 3 giovani" },
  { id: "8", issue_type: "Io sono il Condor!", issue_description: "Acquistare parametri 0 o vecchie glorie" },
  { id: "9", issue_type: "Non spendo soldi per gli acquisti", issue_description: "Acquista giocatori tramite svincolati prestiti o scambi" },
  { id: "10", issue_type: "Rispetta il passato", issue_description: "Acquista giocatori solo provenienti da nazioni a cui la societa' e' legata" },
  { id: "12", issue_type: "Io sono Paratici", issue_description: "Acquista giocatori di prospettiva o dalle giovanili e rivendili per plusvalenze" },
  { id: "13", issue_type: "Accetta tutte le offerte", issue_description: "Accetta ogni offerta che arriva" },
  { id: "14", issue_type: "Operazione giovani", issue_description: "Acquista solo giocatori al di sotto dei 24 anni" },
  { id: "15", issue_type: "Occhio alla perestrojka", issue_description: "Acquista solo giocatori dell'est Europa" },
  { id: "17", issue_type: "Ti presento Giginho", issue_description: "Acquista solo giocatori sudamericani" },
  { id: "18", issue_type: "Sono er figlio de Gaucci", issue_description: "Acquista giocatori o manda osservatori in paesi esotici" },
  { id: "19", issue_type: "Suolo natio", issue_description: "Acquista solo giocatori della stessa nazione del campionato di appartenenza" },
  { id: "20", issue_type: "Operazione RSA", issue_description: "Eta media squadra sopra i 30 anni" },
  { id: "21", issue_type: "Fallimento Sponsor principale", issue_description: "Non spendere il budget trasferimenti fino inizio stagione successica" },
  { id: "22", issue_type: "Cambiamento regole permesso di lavoro", issue_description: "Vendi tutti i giocatori non appartenenti all'area geografica di appartenenza" },
  { id: "23", issue_type: "Illecito sportivo mercato giovanili", issue_description: "Vendi il tuo miglior giocatore under 25" },
  { id: "24", issue_type: "Mal D'Africa", issue_description: "Invia osservatore 9 mesi in Africa e acquista un giocatore africano" },
  { id: "25", issue_type: "Investitori perdono interesse nel team", issue_description: "Per la stagione successiva vendi i giocatori con contratti più elevati" },
  { id: "26", issue_type: "Qui comanda il presidente", issue_description: "Obbligo di giocare con il modulo imposto dal presidente" },
  { id: "27", issue_type: "Trovati un altra squadra", issue_description: "Il presidente non rinnoverà il tuo contratto e" },
  { id: "28", issue_type: "Siamo affiliati!", issue_description: "Prendi in prestito 3 giocatori da squadre con overall più elevato" },
  { id: "29", issue_type: "Licenzia tutto lo staff", issue_description: "Cambia tutti gli scout della squadra" },
  { id: "30", issue_type: "Espansione mercato asiatico", issue_description: "Acquista giocatori di nazionalità asiatica" },
  { id: "31", issue_type: "I love the Commonwealth", issue_description: "Acquista giocatori nordamericani australiani o neozelandesi" },
  { id: "32", issue_type: "Non siamo ambiziosi!", issue_description: "Acquista due giocatori di fama internazionale" },
  { id: "33", issue_type: "Tengo Saudade", issue_description: "Vendi un giocatore sudamericano" },
  { id: "34", issue_type: "Se so bevuti il presidente", issue_description: "Vendi i giocatori più forti per abbassare di tre punti l'overall della squadra" },
  { id: "35", issue_type: "Rivolta tifosi!", issue_description: "Per calmare la piazza acquista un giocatore con overall più alto della squadra" },
  { id: "36", issue_type: "Voglio solo profitti!", issue_description: "Vendi in ogni finestra di mercato un giocatore per avere un profitto" },
  { id: "37", issue_type: "Rispettiamo UE", issue_description: "Compra solo giocatori dell'UE" },
  { id: "38", issue_type: "Dio salvi la regina", issue_description: "Acquista solo giocatori provenienti dalla Gran Bretagna" },
  { id: "39", issue_type: "Sono figlio di Attila Flagello di Dio!", issue_description: "Acquista solo giocatori scandinavi" },
  { id: "40", issue_type: "Restrizioni sui Giocatori Stranieri (Limitazione di Giocatori Non-Nazionali)", issue_description: "Acquista solo 3 giocatori di nazionalità straniera" }
];

// --- Manager Issues ---
const managerIssueList = [
  { id: "1", issue_type: "SEMPER FIDELIS!", issue_description: "L'ALLENATORE PUO' RIMANERE UN ALTRO ANNO AL SUO POSTO" },
  { id: "3", issue_type: "MY NAME IS ORONZO CANA!", issue_description: "SCEGLI UNA SQUADRA DI BASSA CLASSIFICA IN UNA QUALSIASI LEGA DI PRIMO LIVELLO" },
  { id: "5", issue_type: "SARO' IL VOSTRO SALVATORE", issue_description: "SCEGLI UNA SQUADRA DI SERIE INFERIORE E PORTALA AL SUCCESSO" },
  { id: "7", issue_type: "SO AMICO DER VICHINGO", issue_description: "SCEGLI UNA SQUADRA QUALSIASI DI UN CAMPIONATO SCANDINAVO" },
  { id: "9", issue_type: "MI MANDA GIGINHO FRATELLO DI LECCE", issue_description: "SCEGLI UNA SQUADRA DI UN QUALSIASI CAMPIONATO SUDAMERICANO" },
  { id: "11", issue_type: "SEI LICENZIATO!", issue_description: "IL PRESIDENTE DECIDE DI ESONERARTI. SCEGLI UN ALTRA SQUADRA DI PARI LIVELLO O LIVELLO INFERIORE" },
  { id: "13", issue_type: "MAKE AMERICA GREAT AGAIN", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO AMERICANO" },
  { id: "15", issue_type: "WIN THE BEST", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO OLTREMANICA" },
  { id: "17", issue_type: "SO ER NIPOTE DEL TRAP", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO TEDESCO, AUSTRIACO O IRLANDESE" },
  { id: "19", issue_type: "VAMOS A MATAR COMPANEROS", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO SPAGNOLO O PORTOGHESE" },
  { id: "21", issue_type: "PARIGI O CARA", issue_description: "SEGLI UNA SQUADRA DEL CAMPIONATO FRANCESE" },
  { id: "23", issue_type: "SO REGOLARE COME UN OROLOGIO", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO SVIZZERO" },
  { id: "25", issue_type: "ME CHIAMANO MARCO POLO", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO AUSTRALIANO O ASIATICO" },
  { id: "27", issue_type: "DEUS EX MACHINA", issue_description: "CREA UN TEAM IN UN CAMPIONATO QUALSIASI E PORTALO ALLA GLORIA" },
  { id: "29", issue_type: "MAMMA LI TURCHI", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO OLANDESE" },
  { id: "31", issue_type: "MI MANDA SCIFO", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO BELGA" },
  { id: "33", issue_type: "OCCHIO ALLA PERESTROJKA", issue_description: "SCEGLI UNA SQUADRA DI UN CAMPIONATO DELL'EST EUROPA" },
  { id: "35", issue_type: "OPERAZIONE BARBAROSSA", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO RUSSO" },
  { id: "37", issue_type: "ALLA CONQUISTA DELLA DACIA", issue_description: "SCEGLI UNA SQUADRA DEL CAMPIONATO ROMENO" },
  { id: "39", issue_type: "STA PREPARANDO QUALCHE AZIONE SUI BALCONI...SUI BALCANI", issue_description: "SCEGLI UNA SQUADRA DELL'AREA BALCANICA" }
];

// --- Creazione tabelle e inserimento dati ---
db.serialize(() => {
  // Creazione tabelle
  db.run(`CREATE TABLE IF NOT EXISTS player_issues (id INTEGER PRIMARY KEY, issue_type TEXT, issue_description TEXT)`);
  db.run(`CREATE TABLE IF NOT EXISTS team_issues (id INTEGER PRIMARY KEY, issue_type TEXT, issue_description TEXT)`);
  db.run(`CREATE TABLE IF NOT EXISTS market_issues (id INTEGER PRIMARY KEY, issue_type TEXT, issue_description TEXT)`);
  db.run(`CREATE TABLE IF NOT EXISTS manager_issues (id INTEGER PRIMARY KEY, issue_type TEXT, issue_description TEXT)`);

  // Inserimento dati
  const insertPlayer = db.prepare("INSERT INTO player_issues (id, issue_type, issue_description) VALUES (?, ?, ?)");
  playerIssueList.forEach(p => insertPlayer.run(p.id, p.issue_type, p.issue_description));
  insertPlayer.finalize();

  const insertTeam = db.prepare("INSERT INTO team_issues (id, issue_type, issue_description) VALUES (?, ?, ?)");
  teamIssueList.forEach(t => insertTeam.run(t.id, t.issue_type, t.issue_description));
  insertTeam.finalize();

  const insertMarket = db.prepare("INSERT INTO market_issues (id, issue_type, issue_description) VALUES (?, ?, ?)");
  marketIssueList.forEach(m => insertMarket.run(m.id, m.issue_type, m.issue_description));
  insertMarket.finalize();

  const insertManager = db.prepare("INSERT INTO manager_issues (id, issue_type, issue_description) VALUES (?, ?, ?)");
  managerIssueList.forEach(m => insertManager.run(m.id, m.issue_type, m.issue_description));
  insertManager.finalize();
});

db.close(() => {
  console.log("Database eafc_issues.db creato e popolato con successo!");
});
