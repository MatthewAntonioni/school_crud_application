async function loadEntries(){

const responce = await fetch('https://api.dfg-h.com/api/M_table')

const data = await responce.json();
s
const table = document.getElementById("S_table_M")

data.forEach(entry =>{

const row = table.insertRow();

row.insertCell().textContent = entry[0];
row.insertCell().textContent = entry[1];
row.insertCell().textContent = entry[2];
row.insertCell().textContent = entry[3];

})
}

loadEntries();
