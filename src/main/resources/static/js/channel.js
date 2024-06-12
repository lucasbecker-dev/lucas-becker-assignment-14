function init() {
    if (sessionStorage.getItem('user') == null) {
        returnToWelcome();
    }
    const channelId = window.location.pathname.split('/')[2];
    getChannelName(channelId);
    getAndDisplayMessages(channelId);
    setInterval(() => {
        getAndDisplayMessages(channelId);
    }, 5000);
}

function checkEnter(event) {
    if (event.keyCode === 13) {
        const messageInput = document.getElementById('messageInput');
        createMessage(messageInput.value);
        messageInput.value = '';
        messageInput.setSelectionRange(0, 0);
    }
}

function getAndDisplayMessages(channelId) {
    fetch('/channels/' + channelId + '/messages', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            const messageList = document.getElementById('messageList');
            messageList.innerHTML = '';
            data.forEach(message => {
                const span = document.createElement('span');
                const strong = document.createElement('strong');
                const p = document.createElement('p');
                const userName = document.createTextNode(message.user.name + ': ');
                strong.appendChild(userName);
                const messageContent = document.createTextNode(message.content);
                p.appendChild(strong);
                p.appendChild(messageContent);
                span.appendChild(p);
                messageList.appendChild(span);
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function createMessage(content) {
    const channelId = window.location.pathname.split('/')[2];
    const userName = sessionStorage.getItem('user');
    const newMessage = {
        content: content,
        userName: userName,
        channelId: channelId,
    };

    fetch('/message', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newMessage),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Message created successfully:', data);
        })
        .then(() => {
            getAndDisplayMessages(channelId);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function getChannelName(channelId) {
    fetch('/channels/' + channelId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('channelName').textContent = data.name;
            document.getElementById('currentUser').textContent = 'Current User: ' + sessionStorage.getItem('user');
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function returnToWelcome() {
    window.location.href = "/welcome";
    return;
}
