const url = `http://localhost:8080/user`;


function show(user) {
    let tab = `
    <thead>
        <th>#</th>
        <th>Username</th>
        <th>Password</th>
    </thead>`;

    for (let user of users) {
        tab +=
            `
        <tr>
            <td scope="row">${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
        </tr>
        `
    }
    document.getElementById("user").innerHTML = tab;
}

async function getApi(url) {
    const response = await fetch(url, { method: "GET" });

    let data = await response.json();
    show(data);
}

getApi(url);
