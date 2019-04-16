function validate() {
    var validators = [
        function () {
            return nameValid(document.getElementById("first_name"))
        },
        function () {
            return lastNameValid(document.getElementById("last_name"))
        },
        function () {
            return emailValid(document.getElementById("email_address"))
        },
        function () {
            return passwordValid(document.getElementById("inputtedPassword"))
        },
        function () {
            return passwordConfirm(document.getElementById("inputtedPassword"), document.getElementById("confirmedPassword"));
        }
    ];
    var valid = true;
    for (i = 0; i < validators.length; i++) {
        if (!validators[i]()) {
            valid = false;
        }
    }
    return valid;

}


function nameValid(name) {
    if (name.value == "") {
        setErrorMessage("nameError", "Please provide your name!");
        return false;
    }
    setErrorMessage("nameError", "");
    return true;
}

function lastNameValid(lastName) {
    if (lastName.value == "") {
        setErrorMessage("lastNameError", "Please provide your last name");
        return false;
    }
    setErrorMessage("lastNameError", "");
    return true;
}

function emailValid(email) {
    if (email.value == "") {
        setErrorMessage("emailError", "Please provide your email");
        return false;
    }
    var atPosition = email.value.indexOf("@");
    var dotPosition = email.value.lastIndexOf(".");
    if (atPosition == -1 || dotPosition <= atPosition) {
        setErrorMessage("emailError", "Please enter correct email, like myCoolEmail@example.com");
        return false;
    }
    setErrorMessage("emailError", "");
    return true;
}

function passwordValid(password) {
    if (password.value == "") {
        setErrorMessage("passwordError", "Please provide your password");
        return false;
    }
    var passwordPattern = /^\S{8,}$/;
    if (!password.value.match(passwordPattern)) {
        setErrorMessage("passwordError", "Please provide correct password it must have from 8 to 14 symbols of letters or numbers");
        return false;
    }
    setErrorMessage("passwordError", "");
    return true;
}

function passwordConfirm(password, confirmedPassword) {
    if (password.value === confirmedPassword.value) {
        setErrorMessage("confirmedPasswordError", "")
        return true;

    }
    setErrorMessage("confirmedPasswordError", "Passwords doesn't match!")
    return false;

}

function setErrorMessage(elementID, message) {
    document.getElementById(elementID).innerHTML = message;
}
