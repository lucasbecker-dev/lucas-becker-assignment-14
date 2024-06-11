function init() {
    if (sessionStorage.getItem('user') == null) {
        window.location.href = "/welcome";
        return;
    }
    const channelId = window.location.pathname.split('/')[2];
    getChannelName(channelId);
    setInterval(() => {
        getAndDisplayMessages(channelId);
    }, 1000);
}

function checkEnter(event) {
    if (event.keyCode === 13) {
        const messageInput = document.getElementById('messageInput');
        createMessage(messageInput.value);
        messageInput.value = '';
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
                const p = document.createElement('p');
                p.textContent = message.content;
                messageList.appendChild(p);
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function createMessage(content) {
    const channelId = window.location.pathname.split('/')[2];
    const userId = sessionStorage.getItem('user');
    const newMessage = {
        content: content,
        userId: userId,
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
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}