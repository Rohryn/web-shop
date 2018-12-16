$(document).ready(function () {
    $('#submit').click(function (e) {
        var validators = [
            function () {
                return jnameValid($("#firstName").val())
            },
            function () {
                return jlastNameValid($("#lastName").val())
            },
            function () {
                return jemailValid($("#email").val())
            },
            function () {
                return jpasswordValid($('#password').val())
            },
            function () {
                return jpasswordConfirm($('#password').val(), $('#confirmedPassword').val());
            }
        ];
        var valid = true;
        for (i = 0; i < validators.length; i++) {
            if (!validators[i]()) {
                valid = false;
            }
        }
        if (valid) {
            $("#regForm").submit();
        } else {
            $("#regForm").submit(function (e) {
                e.preventDefault(e);
            });
        }
    });
});


function jnameValid(name) {
    if (name == "") {
        jsetErrorMessage("nameError", "Please provide your name!");
        return false;
    }
    jsetErrorMessage("nameError", "");
    return true;
}

function jlastNameValid(lastName) {
    if (lastName == "") {
        jsetErrorMessage("lastNameError", "Please provide your last name");
        return false;
    }
    jsetErrorMessage("lastNameError", "");
    return true;
}

function jemailValid(email) {
    if (email == "") {
        jsetErrorMessage("emailError", "Please provide your email");
        return false;
    }
    var atPosition = email.value.indexOf("@");
    var dotPosition = email.value.lastIndexOf(".");
    if (atPosition == -1 || dotPosition <= atPosition) {
        jsetErrorMessage("emailError", "Please enter correct email, like example@help.com");
        return false;
    }
    jsetErrorMessage("emailError", "");
    return true;
}

function jpasswordValid(password) {
    if (password == "") {
        jsetErrorMessage("passwordError", "Please provide your password");
        return false;
    }
    var passwordPattern = /^\w{8,14}$/;
    if (!password.match(passwordPattern)) {
        setErrorMessage("passwordError", "Please provide correct password it must have from 8 to 14 symbols of letters or numbers");
        return false;
    }
    jsetErrorMessage("passwordError", "");
    return true;
}

function jpasswordConfirm(password, confirmedPassword) {
    if (password === confirmedPassword.value) {
        setErrorMessage("confirmedPasswordError", "")
        return true;

    }
    jsetErrorMessage("confirmedPasswordError", "Passwords doesn't match!")
    return false;

}

function jsetErrorMessage(elementID, message) {
    $("#" + elementID).html(message);

}
