async function loadEntries(){

console.log("JavaScript loaded");

const responce = await fetch('https://dfg-h/api/s_table'); //this is telling the program where to get the data

console.log("JavaScript loaded");

const data = await responce.json(); //this is telling the program to wait until you get the data and convert it to json

console.log("JavaScript loaded");

const table = document.getElementById("s_table"); //this tells the program where to put the data

console.log("JavaScript loaded");

data.forEach(entry =>{ //a for each

console.log("JavaScript loaded");

const row = table.insertRow(); // makes a new row in the table

console.log("JavaScript loaded");

row.insertCell().textContent = entry[0]; //this is telling the program to put the data in the first cell
row.insertCell().textContent = entry[1]; //this is telling the program to put the data in the second cell
row.insertCell().textContent = entry[2]; //this is telling the program to put the data in the third cell

})

}

console.log("JavaScript loaded");

loadEntries(); //this is telling the program to run the function