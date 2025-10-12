const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');
const sqlite3 = require('sqlite3').verbose();

let db;
let win;

function createWindow() {
  win = new BrowserWindow({
    width: 1000,
    height: 700,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'), // <-- preload corretto
      nodeIntegration: false,
      contextIsolation: true, // deve essere true con contextBridge
    },
  });

  win.loadFile(path.join(__dirname, 'src', 'index.html'));
}

// Apri il DB quando Electron è pronto
app.whenReady().then(() => {
  const dbPath = app.isPackaged
    ? path.join(process.resourcesPath, 'db', 'eafc_issues.db') // percorso exe
    : path.join(__dirname, 'db', 'eafc_issues.db');             // sviluppo

  console.log('Percorso DB:', dbPath);

  db = new sqlite3.Database(dbPath, (err) => {
    if (err) console.error('Errore apertura database:', err.message);
    else console.log('Database aperto correttamente.');
  });

  createWindow();

  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit();
});

// --- IPC handler per comunicazione renderer ---
ipcMain.handle('get-max-id', async (event, tableName) => {
  return new Promise((resolve) => {
    db.get(`SELECT MAX(id) as maxId FROM ${tableName}`, [], (err, row) => {
      if (err) {
        console.error(err);
        resolve(0);
      } else {
        resolve(row ? row.maxId : 0);
      }
    });
  });
});

ipcMain.handle('get-issue-by-id', async (event, tableName, id) => {
  return new Promise((resolve) => {
    db.get(`SELECT * FROM ${tableName} WHERE id = ?`, [id], (err, row) => {
      if (err) {
        console.error(err);
        resolve(null);
      } else {
        resolve(row);
      }
    });
  });
});
