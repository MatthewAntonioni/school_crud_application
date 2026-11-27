const API = location.hostname.endsWith("dfg-h.com") ? "https://api.dfg-h.com" : "";

// only members can be here, everyone else goes back to login
const user = JSON.parse(localStorage.getItem("user"));
if (!user || user.role !== "member") {
    location.href = "../../login/index.html";
}

function message(text) {
    const m = document.getElementById("message");
    m.textContent = text;
    m.className = "error";
}

// load a member's unpaid fines and put them in the table
async function loadFines() {
    const res = await fetch(API + "/api/fines/" + user.id);
    if (!res.ok) throw new Error("Couldn't load your fines");
    const fines = await res.json();

    // nothing owed
    if (fines.length === 0) {
        document.getElementById("total").textContent = "You don't owe anything";
        return;
    }

    const body = document.querySelector("#fines tbody");
    let total = 0;

    for (const f of fines) {
        const row = body.insertRow();
        row.insertCell().textContent = f.title;
        row.insertCell().textContent = f.daysOverdue;
        row.insertCell().textContent = f.dateIssued;
        const amount = row.insertCell();
        amount.textContent = "$" + Number(f.amount).toFixed(2);
        amount.className = "money";
        total += Number(f.amount);
    }

    // show the table, the total and the pay button
    document.getElementById("fines").hidden = false;
    document.getElementById("total").textContent = "Total owed: $" + total.toFixed(2);
    document.getElementById("pay").hidden = false;
}

// pay button: pay everything, popup, back to home
document.getElementById("pay").addEventListener("click", async () => {
    if (!confirm("Pay all your fines now?")) return;
    try {
        const res = await fetch(API + "/api/fines/" + user.id + "/pay", {method: "POST"});
        const body = await res.json();
        if (!res.ok) throw new Error(body.error || "Something went wrong");
        alert(body.message);
        location.href = "../index.html";
    } catch (err) {
        message(err.message);
    }
});

loadFines().catch(err => message(err.message));