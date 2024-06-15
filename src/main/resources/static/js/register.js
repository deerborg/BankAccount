document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const phone = document.getElementById('phone').value;
    const birthDate = document.getElementById('birthDate').value;

    fetch('/customer/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            phone: phone,
            birthDate: birthDate,
        }),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            window.location.href = '/welcome';
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});