let accountId = null;
let iban = null;
window.onload = function() {
    fetch('/customer/get-customer')
        .then(response => response.json())
        .then(data => {
            if (data.status === true) {
                var customerId = data.data;
                createAccount(customerId);
            } else {
                console.error('Failed to retrieve customer data');
            }
        })
        .catch(error => console.error('Error:', error));
}

function createAccount(customerId) {
    fetch('/account', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            customer: {
                id: customerId
            }
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === true) {
                accountId = data.data.id;
            } else {
                console.error('Failed to create account:', data.message);
            }
        })
        .catch(error => console.error('Error creating account:', error));
}

function sendBalance() {
    let iban = document.getElementById("iban").value;
    let balance = document.getElementById("balance").value;
    fetch('/account/transfer', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: accountId,
            iban: iban,
            balance: balance
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log('Add balance response:', data);
            if (data.status === true) {
                if (data.data.balance >= 1000000.0) {
                    data.data.balance = 1000000.0; // Limit balance to 1,000,000.0

                    // Create a message element
                    var messageDiv = document.createElement('div');
                    messageDiv.textContent = 'Balance has been capped at 1,000,000.0 for security reasons. Further increases will not be processed, including transactions involving this balance.';
                    messageDiv.style.backgroundColor = '#dc3545'; // Red color
                    messageDiv.style.color = '#fff';
                    messageDiv.style.padding = '10px';
                    messageDiv.style.borderRadius = '5px';
                    messageDiv.style.marginTop = '10px'; // Space from the button

                    // Append message to main section
                    var mainSection = document.querySelector('main');
                    mainSection.appendChild(messageDiv);
                }
                showSendingMessage();
            } else {
                console.error('Failed to add balance:', data.message);
            }
        })
        .catch(error => console.error('Error adding balance:', error));
}

function showSendingMessage() {
    // Create a message element
    var messageDiv = document.createElement('div');
    messageDiv.textContent = 'Sending';
    messageDiv.style.backgroundColor = '#28a745'; // Green color
    messageDiv.style.color = '#fff';
    messageDiv.style.padding = '10px';
    messageDiv.style.borderRadius = '5px';
    messageDiv.style.marginTop = '10px'; // Space from the button

    // Append message to main section
    var mainSection = document.querySelector('main');
    mainSection.appendChild(messageDiv);

    // Remove the message after 5 seconds
    setTimeout(function() {
        mainSection.removeChild(messageDiv);
        location.reload();
    }, 1000);
}