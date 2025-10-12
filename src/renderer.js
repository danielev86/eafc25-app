document.addEventListener('DOMContentLoaded', () => {
  const contentDiv = document.getElementById('content');

  const homeLink = document.getElementById('homeLink');
  const playerIssueLink = document.getElementById('playerIssueLink');
  const teamIssueLink = document.getElementById('teamIssueLink');
  const marketIssueLink = document.getElementById('thirdPageLink');
  const managerIssueLink = document.getElementById('managerIssueLink');
  const eafcSliderIssueLink = document.getElementById('eafcSliderIssueLink');

  function generateRandomValue(max) {
    return Math.floor(Math.random() * max) + 1;
  }

  function isEven(num) {
    return num % 2 === 0;
  }

  async function getMaxId(table) {
    return await window.api.getMaxId(table);
  }

  async function getIssueById(table, id) {
    return await window.api.getIssueById(table, id);
  }

  // --- Home Page ---
  function loadHomePage() {
    contentDiv.innerHTML = `
      <h2>Benvenuto in EA SPORTS FC 26 Issue Generator!</h2>
      <p>Questa è una applicazione semplificata per generare issue dinamiche per giocatori, team e calciomercato.</p>
    `;
  }

  // --- Player Issue ---
  async function loadPlayerIssuePage() {
    contentDiv.innerHTML = `
      <button id="generatePlayer">Genera Player Issue</button>
      <p id="playerType"></p>
      <p id="playerNum"></p>
      <p id="playerDesc"></p>
    `;

    document.getElementById('generatePlayer').addEventListener('click', async () => {
      const maxId = await getMaxId('player_issues');
      if (!maxId) return alert('Errore nel recupero del massimo ID!');
      const randomId = generateRandomValue(maxId);

      if (isEven(randomId)) {
        contentDiv.querySelector('#playerDesc').innerText = 'Nessun imprevisto generato!';
        contentDiv.querySelector('#playerType').innerText = '';
        contentDiv.querySelector('#playerNum').innerText = '';
      } else {
        const info = await getIssueById('player_issues', randomId);
        if (!info) {
		   contentDiv.querySelector('#playerDesc').innerText = 'Nessun imprevisto generato!';
			contentDiv.querySelector('#playerType').innerText = '';
			contentDiv.querySelector('#playerNum').innerText = '';             
		}else{
			contentDiv.querySelector('#playerType').innerText = info.issue_type;
			contentDiv.querySelector('#playerNum').innerText = 'Giocatore numero: ' + generateRandomValue(18);
			contentDiv.querySelector('#playerDesc').innerText = info.issue_description;
		}
      }
    });
  }

  // --- Team Issue ---
  async function loadTeamIssuePage() {
    contentDiv.innerHTML = `
      <button id="generateTeam">Genera Team Issue</button>
      <p id="teamType"></p>
      <p id="teamDesc"></p>
    `;

    document.getElementById('generateTeam').addEventListener('click', async () => {
      const maxId = await getMaxId('team_issues');
      if (!maxId) return alert('Errore nel recupero del massimo ID!');
      const randomId = generateRandomValue(maxId);
      const info = await getIssueById('team_issues', randomId);
      if (!info) {
		  contentDiv.querySelector('#teamType').innerText = 'Nessun imprevisto generato!';
		  contentDiv.querySelector('#teamDesc').innerText = '';
	  }else{
		  contentDiv.querySelector('#teamType').innerText = info.issue_type;
		  contentDiv.querySelector('#teamDesc').innerText = info.issue_description;
	  }
    });
  }

  // --- Market Issue ---
  async function loadMarketIssuePage() {
    contentDiv.innerHTML = `
      <button id="generateMarket">Genera Market Issue</button>
      <p id="marketType"></p>
      <p id="marketDesc"></p>
    `;

    document.getElementById('generateMarket').addEventListener('click', async () => {
      const maxId = await getMaxId('market_issues');
      if (!maxId) return alert('Errore nel recupero del massimo ID!');
      const randomId = generateRandomValue(maxId);
      const info = await getIssueById('market_issues', randomId);
      if (!info){
		  contentDiv.querySelector('#marketType').innerText = 'Nessun imprevisto generato!';
		  contentDiv.querySelector('#marketDesc').innerText = '';
	  }else{
		  contentDiv.querySelector('#marketType').innerText = info.issue_type;
		  contentDiv.querySelector('#marketDesc').innerText = info.issue_description;
	  }
    });
  }

  // --- Manager Issue ---
  async function loadManagerIssuePage() {
    contentDiv.innerHTML = `
      <button id="generateManager">Genera Manager Issue</button>
      <p id="managerType"></p>
      <p id="managerDesc"></p>
    `;

    document.getElementById('generateManager').addEventListener('click', async () => {
      const maxId = await getMaxId('manager_issues');
      if (!maxId) return alert('Errore nel recupero del massimo ID!');
      const randomId = generateRandomValue(maxId);
      const info = await getIssueById('manager_issues', randomId);
      if (!info) {
		contentDiv.querySelector('#managerType').innerText = 'Nessun imprevisto generato!';
		contentDiv.querySelector('#managerDesc').innerText = '';
	  }else{
		contentDiv.querySelector('#managerType').innerText = info.issue_type;
		contentDiv.querySelector('#managerDesc').innerText = info.issue_description;
	  }
    });
  }

  // --- Slider Page (static) ---
  function loadEaFcSliderPage() {
    contentDiv.innerHTML = `<p>Slider FC qui...</p>`;
  }

  // --- Eventi di navigazione ---
  homeLink.addEventListener('click', loadHomePage);
  playerIssueLink.addEventListener('click', loadPlayerIssuePage);
  teamIssueLink.addEventListener('click', loadTeamIssuePage);
  marketIssueLink.addEventListener('click', loadMarketIssuePage);
  managerIssueLink.addEventListener('click', loadManagerIssuePage);
  eafcSliderIssueLink.addEventListener('click', loadEaFcSliderPage);

  // --- Carica home all’avvio ---
  loadHomePage();
});
