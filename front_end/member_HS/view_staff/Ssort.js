async function loadEntries(){

const responce = await fetch('http://dfg-h/api/s_table'); //this is telling the program where to get the data

const data = await responce.json(); //this is telling the program to wait until you get the data and convert it to json

const table = document.getElementById("s_table"); //this tells the program where to put the data

data.forEach(entry =>{ //a for each

const row = table.insertRow(); // makes a new row in the table

row.insertCell().textContent = entry[0]; //this is telling the program to put the data in the first cell
row.insertCell().textContent = entry[1]; //this is telling the program to put the data in the second cell
row.insertCell().textContent = entry[2]; //this is telling the program to put the data in the third cell

})

}

loadEntries(); //this is telling the program to run the function