window.onload = function() {
    // Attach event listeners
    var registerForm = document.forms['registrationForm'];
    if (registerForm) {
        registerForm.onsubmit = function() {
            return validateRegistrationForm();
        };
    }
}

function validateRegistrationForm() {
    var form = document.forms['registrationForm'];
    var email = form['username'].value;
    var password = form['password'].value;
    var errorDiv = document.getElementById('error');

    // Simple email regex for basic validation
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    var isValidEmail = emailRegex.test(email);

    if (!isValidEmail) {
        errorDiv.innerHTML = 'Please enter a valid email address.';
        return false;
    }

    if (password.length < 6) {
        errorDiv.innerHTML = 'Password must be at least 6 characters long.';
        return false;
    }

    // If all validations pass
    errorDiv.innerHTML = '';
    return true;
}

// Additional functions can be added here as needed
