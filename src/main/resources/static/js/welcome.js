function init() {
    checkUser();
    getAndDisplayChannels();
}

function getAndDisplayChannels() {
    fetch('/channels', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            const channelList = document.getElementById('channelList');
            channelList.innerHTML = '';
            data.forEach(channel => {
                const li = document.createElement('li');
                const a = document.createElement('a');
                a.textContent = channel.name;
                a.href = "/channel/" + channel.id;
                li.appendChild(a);
                channelList.appendChild(li);
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function checkUser() {
    const user = sessionStorage.getItem('user');
    if (!user) {
        createNewUser();
    } else {
        updateWelcomeMessage();
    }
}

function createNewUser() {
    const name = prompt("Please enter your name");
    if (name != null) {
        sessionStorage.setItem('user', name);
        const newUser = {
            name: name
        };
        fetch('/user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newUser),
        })
            .then(response => response.json())
            .then(data => {
                console.log('User created successfully: ', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            })
    }
    updateWelcomeMessage();
}

function updateWelcomeMessage() {
    const user = sessionStorage.getItem('user');
    const welcome = document.getElementById('welcome');
    welcome.innerHTML = 'Welcome ' + user;
}
