const sqlite3 = require('sqlite3').verbose();
const path = require('path');
const { app } = require('electron');

// Percorso al DB
let dbPath;

// Se siamo in sviluppo
if (!app.isPackaged) {
    dbPath = path.join(__dirname, 'db', 'eafc_issues.db');
} else {
    // In exe: extraResources va in resourcesPath
    dbPath = path.join(process.resourcesPath, 'db', 'eafc_issues.db');
}

console.log('Percorso DB:', dbPath);

const db = new sqlite3.Database(dbPath, (err) => {
    if (err) console.error('Errore apertura database:', err.message);
    else console.log('Database aperto correttamente:', dbPath);
});

function getIssueById(tableName, id, callback) {
    db.get(`SELECT * FROM ${tableName} WHERE id = ?`, [id], (err, row) => {
        if (err) console.error(err);
        callback(row || null);
    });
}

function getAllIssues(tableName, callback) {
    db.all(`SELECT * FROM ${tableName}`, [], (err, rows) => {
        if (err) console.error(err);
        callback(rows || []);
    });
}

function getMaxId(tableName, callback) {
    db.get(`SELECT MAX(id) as maxId FROM ${tableName}`, [], (err, row) => {
        if (err) console.error(err);
        callback(row ? row.maxId : 0);
    });
}

module.exports = { getIssueById, getAllIssues, getMaxId };
