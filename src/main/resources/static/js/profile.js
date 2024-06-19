let accountId = null;
function toggleMenu() {
    var menu = document.getElementById("nav-menu");
    menu.classList.toggle("show");
}

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
            console.log('Account creation response:', data);
            if (data.status === true) {
                document.getElementById('fullname').textContent = data.data.fullname;
                document.getElementById('balance').value = data.data.balance;
                document.getElementById('iban').value = data.data.iban;

                // addBalance fonksiyonunu çağır ve hesap ID'sini geç
                accountId = data.data.id;
            } else {
                console.error('Failed to create account:', data.message);
            }
        })
        .catch(error => console.error('Error creating account:', error));
}

function addBalance() {
    fetch('/account/add-balance', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: accountId,
            balance: 10000
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
                document.getElementById('balance').value = data.data.balance;
            } else {
                console.error('Failed to add balance:', data.message);
            }
        })
        .catch(error => console.error('Error adding balance:', error));
}