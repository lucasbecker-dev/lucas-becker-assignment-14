function getAndDisplayChannels() {
    fetch('/channels', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            var channelList = document.getElementById('channelList');
            data.forEach(channel => {
                var li = document.createElement('li');
                var a = document.createElement('a');
                a.textContent = channel.name;
                a.href = "/channels/" + channel.id;
                li.appendChild(a);
                channelList.appendChild(li);
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function checkUser() {
    var user = sessionStorage.getItem('user')
    if (!user) {
        var name = prompt("Please enter your name")
        if (name != null) {
            sessionStorage.setItem('user', name)
            var newUser = {
                name: name
            }
            fetch('/user', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newUser),
            })
                .then(response => response.json())
                .then(data => {
                    console.log('User created successfully: ', data)
                })
                .catch((error) => {
                    console.error('Error:', error)
                })
        }
        createChannels(['General', 'Development', 'Off-topic'])
    }
}

function createChannels(channelNames) {
    var newChannels = channelNames.map(name => ({ name: name }));
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

function init() {
    checkUser()
}