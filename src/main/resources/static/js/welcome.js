function init() {
    checkUser();
    createChannels(['General', 'Development', 'Off-topic']);
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
            console.log(channelList);
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
    }
}

function createChannels(channelNames) {
    const newChannels = channelNames.map(name => ({ name: name }));
    fetch('/channels', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newChannels),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Channels created successfully:', data);
            getAndDisplayChannels();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}
