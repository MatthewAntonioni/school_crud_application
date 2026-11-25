async function loadEntries(){

const responce = await fetch('https://api.dfg-h.com/api/S_table_S'); //this is telling the program where to get the data

const data = await responce.json(); //this is telling the program to wait until you get the data and convert it to json

const table = document.getElementById("S_table_S"); //this tells the program where to put the data

data.forEach(entry =>{

    const row = table.insertRow();

    row.insertCell().textContent = entry[0];
    row.insertCell().textContent = entry[1];
    row.insertCell().textContent = entry[2];
    row.insertCell().textContent = entry[3];
    row.insertCell().textContent = entry[4]; //this is telling the program to put the data in the first cell

})

}

loadEntries();