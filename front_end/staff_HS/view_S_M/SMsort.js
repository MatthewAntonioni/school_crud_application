async function loadEntries(){

const responce = await fetch('https://api.dfg-h.com/api/S_table_S'); //this is telling the program where to get the data

const data = await responce.json(); //this is telling the program to wait until you get the data and convert it to json

const table = document.getElementById("S_table_S"); //this tells the program where to put the data

data.forEach(entry =>{

    const row = table.insertRow();

    row.insertCell().textcontent = entry[0];
    row.insertCell().textcontent = entry[1];
    row.insertCell().textcontent = entry[2];
    row.insertCell().textcontent = entry[3];
    row.insertCell().textcontent = entry[4]; //this is telling the program to put the data in the first cell

})

}