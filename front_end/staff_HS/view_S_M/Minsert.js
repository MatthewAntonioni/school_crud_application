async function loadEntries(){

const responce = await fetch('https://api.dfg-h.com/api/M_table')

const data = await responce.json();

const table = document.getElementById("S_table_M")

data.forEach(entry =>{

const row = table.insertRow();

row.insertCell().textContent = entry[0];
row.insertCell().textContent = entry[1];
row.insertCell().textContent = entry[2];
row.insertCell().textContent = entry[3];
row.insertCell().textContent = entry[4];

const button = document.createElement("button");
button.textContent = "Revoke";

button.onclick = () => {revokeMember(entry[0])};

row.insertCell().appendChild(button);

})
}

loadEntries();

async function revokeMember(id){

    const response = await fetch('https://api.dfg-h.com/api/S_table_M/${id}/revoke',{

        method: "PATCH"

    }
);

if (response.ok){

    location.reload();

}else{

    console.error("failed to remove member")

}

}
