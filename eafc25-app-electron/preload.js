const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('api', {
  getMaxId: (table) => ipcRenderer.invoke('get-max-id', table),
  getIssueById: (table, id) => ipcRenderer.invoke('get-issue-by-id', table, id)
});