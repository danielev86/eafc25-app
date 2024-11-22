document.addEventListener('DOMContentLoaded', function() {
    const homeLink = document.getElementById('homeLink');
    const playerIssueLink = document.getElementById('playerIssueLink');
    const teamIssueLink = document.getElementById('teamIssueLink');
    const thirdPageLink = document.getElementById('thirdPageLink');
    const contentDiv = document.getElementById('content');

    const playerIssueList = [ {
        "id": "1",
        "issue_type": "RAFFREDDORE",
        "issue_description": "3 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "2",
        "issue_type": "INFLUENZA",
        "issue_description": "7 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "3",
        "issue_type": "VIRUS",
        "issue_description": "14 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "4",
        "issue_type": "COVID-19",
        "issue_description": "21 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "6",
        "issue_type": "GRAVE INFEZIONE VIRALE",
        "issue_description": "1 MESE FUORI PER INFORTUNEO"
       },
       {
        "id": "7",
        "issue_type": "CAVIGLIA SLOGATA",
        "issue_description": "20 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "8",
        "issue_type": "STIRAMENTO ALLA CAVIGLIA",
        "issue_description": "14 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "9",
        "issue_type": "CONTUSIONE AL PIEDE",
        "issue_description": "15 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "11",
        "issue_type": "STIRAMENTO GINOCCHIO",
        "issue_description": "10 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "12",
        "issue_type": "DISTORSIONE GINOCCHIO",
        "issue_description": "30 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "13",
        "issue_type": "INFIAMMAZIONE ROTULA",
        "issue_description": "20 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "14",
        "issue_type": "STRAPPO LEGAMENTO GINOCCHIO",
        "issue_description": "60 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "16",
        "issue_type": "STIRAMENTO BICIPITE FEMORALE",
        "issue_description": "7 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "17",
        "issue_type": "CONTUSIONE COSCIA",
        "issue_description": "5 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "18",
        "issue_type": "STIRAMENTO MUSCOLO COSCIA",
        "issue_description": "14 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "19",
        "issue_type": "STIRAMENTO INGUINE",
        "issue_description": "14 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "21",
        "issue_type": "STRAPPO MUSCOLO INGUINE",
        "issue_description": "25 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "22",
        "issue_type": "CONTUSIONE COSTOLA",
        "issue_description": "3 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "23",
        "issue_type": "STIRAMENTO ALLA SCHIENA",
        "issue_description": "14 GIORNI FUORI PER INFORTUNEO"
       },
       {
        "id": "24",
        "issue_type": "SCANDALO SOCIAL",
        "issue_description": "15 GIORNI PER COMPORTAMENTO SCORRETTO"
       },
       {
        "id": "26",
        "issue_type": "CREDO IN TE",
        "issue_description": "SCHIERA TITOLARE UNO DEI PEGGIORI GIOCATORI DELLA ROSA"
       },
       {
        "id": "27",
        "issue_type": "RITARDO ALLENAMENTO",
        "issue_description": "7 GIORNI FUORI PER COMPORTAMENTO NON PROFESSIONALE"
       },
       {
        "id": "28",
        "issue_type": "BERE AL PARCO",
        "issue_description": "LICENZIA IL GIOCATORE PIU' FORTE E I DUE PIU' DEBOLI DEL VIVAIO"
       },
       {
        "id": "29",
        "issue_type": "QUANDO TOCCA A NOI",
        "issue_description": "SCHIERA LE RISERVE PER LA PROSSIMA PARTITA"
       },
       {
        "id": "31",
        "issue_type": "SQUALIFICA PER INGIURIA ARBITRO",
        "issue_description": "IL GIOCATORE E' SQUALIFICATO PER 2 PARTITE PER OFFESE ALL'ARBITRO"
       }];
    const teamIssueList = [
        {
         "id": "1",
         "issue_type": "DEBITO SOCIETA 10%",
         "issue_description": "RIDUZIONE BUDGET 10%"
        },
        {
         "id": "2",
         "issue_type": "DEBITO SOCIETA 30%",
         "issue_description": "RIDUZIONE BUDGET 30%"
        },
        {
         "id": "3",
         "issue_type": "DEBITO SOCIETA 50%",
         "issue_description": "RIDUZIONE BUDGET 50%"
        },
        {
         "id": "4",
         "issue_type": "DEBITO SOCIETA 70%",
         "issue_description": "RIDUZIONE BUDGET 70%"
        },
        {
         "id": "6",
         "issue_type": "DEBITO SOCIETA 100%",
         "issue_description": "BANCAROTTA"
        },
        {
         "id": "7",
         "issue_type": "BLOCCO MERCATO",
         "issue_description": "BLOCCO MERCATO"
        },
        {
         "id": "8",
         "issue_type": "CAMBIO SOCIETA AUMENTO 10%",
         "issue_description": "AUMENTO BUDGET 10%"
        },
        {
         "id": "9",
         "issue_type": "CAMBIO SOCIETA AUMENTO 30%",
         "issue_description": "AUMENTO BUDGET 30%"
        },
        {
         "id": "11",
         "issue_type": "CAMBIO SOCIETA AUMENTO 50%",
         "issue_description": "AUMENTO BUDGET 50%"
        },
        {
         "id": "12",
         "issue_type": "CAMBIO SOCIETA AUMENTO 70%",
         "issue_description": "AUMENTO BUDGET 70%"
        },
        {
         "id": "13",
         "issue_type": "CAMBIO SOCIETARIO FONDO ARABO\/CINESE",
         "issue_description": "AUMENTO BUDGET 250 MILIONI EURO"
        },
        {
         "id": "14",
         "issue_type": "PRESIDENTE PATRIOTTICO",
         "issue_description": "SCHIERARE IN CAMPO ALMENO 6 GIOCATORI DELLA PROPRIA NAZIONE DI APPARTENENZA"
        },
        {
         "id": "16",
         "issue_type": "COSTRUZIONE NUOVO STADIO",
         "issue_description": "RIDUZIONE 50% DEL BUDGET TRASFERIMENTI PER COSTUZIONE NUOVO STADIO"
        }
       ];
    const thirdPageList = [
            {
            "id": "1",
            "issue_type": "Nessun imprevisto",
            "issue_description": "Nessun imprevisto!"
           },
           {
            "id": "2",
            "issue_type": "Arrivano gli americani",
            "issue_description": "Compra giocatori di prospettiva per far plusvlenze"
           },
           {
            "id": "3",
            "issue_type": "Arrivano gli sceicchi",
            "issue_description": "Spendi tutto il budget per vincere un trofeo"
           },
           {
            "id": "4",
            "issue_type": "Presidente patriottico",
            "issue_description": "Schierare in campo almeno 6 giocatori della propria nazione di appartenenza"
           },
           {
            "id": "5",
            "issue_type": "Operazione rifondazione",
            "issue_description": "Rifondare la squadra per imposizione societaria"
           },
           {
            "id": "7",
            "issue_type": "Operazione giovanili",
            "issue_description": "Schierare nella formazione almeno 3 giovani"
           },
           {
            "id": "8",
            "issue_type": "Io sono il Condor!",
            "issue_description": "Acquistare parametri 0 o vecchie glorie"
           },
           {
            "id": "9",
            "issue_type": "Non spendo soldi per gli acquisti",
            "issue_description": "Acquista giocatori tramite svincolati prestiti o scambi"
           },
           {
            "id": "10",
            "issue_type": "Rispetta il passato",
            "issue_description": "Acquista giocatori solo provenienti da nazioni a cui la societa' e' legata"
           },
           {
            "id": "12",
            "issue_type": "Io sono Paratici",
            "issue_description": "Acquista giocatori di prospettiva o dalle giovanili e rivendili per plusvalenze"
           },
           {
            "id": "13",
            "issue_type": "Accetta tutte le offerte",
            "issue_description": "Accetta ogni offerta che arriva"
           },
           {
            "id": "14",
            "issue_type": "Operazione giovani",
            "issue_description": "Acquista solo giocatori al di sotto dei 24 anni"
           },
           {
            "id": "15",
            "issue_type": "Occhio alla perestrojka",
            "issue_description": "Acquista solo giocatori dell'est Europa"
           },
           {
            "id": "17",
            "issue_type": "Ti presento Giginho",
            "issue_description": "Acquista solo giocatori sudamericani"
           },
           {
            "id": "18",
            "issue_type": "Sono er figlio de Gaucci",
            "issue_description": "Acquista giocatori o manda osservatori in paesi esotici"
           },
           {
            "id": "19",
            "issue_type": "Suolo natio",
            "issue_description": "Acquista solo giocatori della stessa nazione del campionato di appartenenza"
           },
           {
            "id": "20",
            "issue_type": "Operazione RSA",
            "issue_description": "Eta media squadra sopra i 30 anni"
           },
           {
            "id": "21",
            "issue_type": "Fallimento Sponsor principale",
            "issue_description": "Non spendere il budget trasferimenti fino inizio stagione successica"
           },
           {
            "id": "22",
            "issue_type": "Cambiamento regole permesso di lavoro",
            "issue_description": "Vendi tutti i giocatori non appartenenti all'area geografica di appartenenza"
           },
           {
            "id": "23",
            "issue_type": "Illecito sportivo mercato giovanili",
            "issue_description": "Vendi il tuo miglior giocatore under 25"
           },
           {
            "id": "24",
            "issue_type": "Mal D'Africa",
            "issue_description": "Invia osservatore 9 mesi in Africa e acquista un giocatore africano"
           },
           {
            "id": "25",
            "issue_type": "Investitori perdono interesse nel team",
            "issue_description": "Per la stagione successiva vendi i giocatori con contratti piÃ¹ elevati"
           },
           {
            "id": "26",
            "issue_type": "Qui comanda il presidente",
            "issue_description": "Obbligo di giocare con il modulo imposto dal presidente"
           },
           {
            "id": "27",
            "issue_type": "Trovati un altra squadra",
            "issue_description": "Il presidente non rinnoverÃ  il tuo contratto e"
           },
           {
            "id": "28",
            "issue_type": "Siamo affiliati!",
            "issue_description": "Prendi in prestito 3 giocatori da squadre con overall piÃ¹ elevato"
           },
           {
            "id": "29",
            "issue_type": "Licenzia tutto lo staff",
            "issue_description": "Cambia tutti gli scout della squadra"
           },
           {
            "id": "30",
            "issue_type": "Espansione mercato asiatico",
            "issue_description": "Acquista giocatori di nazionalita asiatica"
           },
           {
            "id": "31",
            "issue_type": "I love the Commonwealth",
            "issue_description": "Acquista giocatori nordamericani australiani o neozelandesi"
           },
           {
            "id": "32",
            "issue_type": "Non siamo ambiziosi!",
            "issue_description": "Acquista due giocatori di fama internazionale"
           },
           {
            "id": "33",
            "issue_type": "Tengo Saudade",
            "issue_description": "Vendi un giocatore sudamericano"
           },
           {
            "id": "34",
            "issue_type": "Se so bevuti il presidente",
            "issue_description": "Vendi i giocatori piÃ¹ forti per abbassare di tre punti l'overall della squadra"
           },
           {
            "id": "35",
            "issue_type": "Rivolta tifosi!",
            "issue_description": "Per calmare la piazza acquista un giocatore con overall piÃ¹ alto della squadra"
           },
           {
            "id": "36",
            "issue_type": "Voglio solo profitti!",
            "issue_description": "Vendi in ogni finestra di mercato un giocatore per avere un profitto"
           },
           {
            "id": "37",
            "issue_type": "Rispettiamo UE",
            "issue_description": "Compra solo giocatori dell'UE"
           },
           {
            "id": "38",
            "issue_type": "Dio salvi la regina",
            "issue_description": "Acquista solo giocatori provenienti dalla Gran Bretagna"
           },
           {
            "id": "39",
            "issue_type": "Sono figlio di Attila Flagello di Dio!",
            "issue_description": "Acquista solo giocatori scandinavi"
           }

    ];

    function loadHomePage() {
        contentDiv.innerHTML = `
            <h2>Benvenuto in EA SPORTS FC 25 Issue Generator!</h2>
            <p style= 'text-center'>Questa è una applicazione semplificata che permette la generazione di issue dinamiche per giocatori, team e calciomercato.</p>
        `;
    }

    function loadPlayerIssuePage() {
        const button = document.createElement('button');
        button.innerText = 'Genera Player Issue';
        const label = document.createElement('p');
        const playerNumber = document.createElement('p');
        contentDiv.innerHTML = '';
        contentDiv.appendChild(button);
        contentDiv.appendChild(playerNumber);
        contentDiv.appendChild(label);

        button.addEventListener('click', () => {
            const randomIndex = generateRandomValue(30);
            playerNumber.innerText = 'Giocatore numero: ' +  generateRandomValue(18);
            playerNumber.setAttribute('style', 'margin-top:10px;margin-bottom:0px;');
            label.innerText = playerIssueList[randomIndex].issue_description;
        });
    }

    function loadTeamIssuePage() {
        const button = document.createElement('button');
        button.innerText = 'Genera Team Issue';
        const label = document.createElement('p');
        contentDiv.innerHTML = '';
        contentDiv.appendChild(button);
        contentDiv.appendChild(label);

        button.addEventListener('click', () => {
            const randomIndex = generateRandomValue(16);
            label.innerText = teamIssueList[randomIndex].issue_description;
        });
    }

    function loadThirdPage() {
        const button = document.createElement('button');
        button.innerText = 'Genera Market Issue';
        const label = document.createElement('p');
        contentDiv.innerHTML = '';
        contentDiv.appendChild(button);
        contentDiv.appendChild(label);

        button.addEventListener('click', () => {
            const randomIndex = generateRandomValue(39);
            label.innerText = thirdPageList[randomIndex].issue_description;
        });
    }

    function generateRandomValue(value){
        return Math. floor(Math. random()*value) + 1;
    }

    homeLink.addEventListener('click', loadHomePage);
    playerIssueLink.addEventListener('click', loadPlayerIssuePage);
    teamIssueLink.addEventListener('click', loadTeamIssuePage);
    thirdPageLink.addEventListener('click', loadThirdPage);

    // Carica la Home page inizialmente
    loadHomePage();
});
