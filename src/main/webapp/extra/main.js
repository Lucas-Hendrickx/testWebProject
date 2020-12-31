window.addEventListener("load", initPage, false);

function initPage() {
    document.addEventListener("blur", checkField, true);
    document.addEventListener("submit", finalValidation, false);
}

function finalValidation(event) {
    let fields = event.target.elements;
    let error, hasErrors;
    for (let i = 0; i < fields.length; i++) {
        error = hasError(fields[i]);
        if (error) {
            showError(fields[i], error);
            if (!hasErrors) {
                hasErrors = fields[i];
            }
        }

    }

    if (hasErrors) {
        event.preventDefault();
        hasErrors.focus();
    }

}

function checkField(event) {
    let error = hasError(event.target);
    if (error)
        showError(event.target, error);
    else
        removeError(event.target);
}

function hasError(field) {
    if (field.disabled || field.type === "file" || field.type === "submit")
        return;

    let validity = field.validity;
    if (validity == null || validity.valid) {
        return;
    }

    if (validity.valueMissing) {
        return "Please fill out a value";
    }
    if (validity.typeMismatch) {
        return "Please use the correct input type";
    }
    if (validity.patternMismatch) {
        if (field.type === "email") {
            return "This is not a valid email.";
        }
        if (field.type === "tel") {
            return "This is not a valid phonenumber"
        }
    }
    return "Please complete the form correct";
}

function removeError(field) {
    if (field.classList != null && field.classList.length > 0) {
        field.classList.remove("error");
        let id = field.id;
        let message = document.getElementById("error-for-" + id);
        if (message != null)
            message.parentNode.removeChild(message);
    }
}

function showError(field, error) {
    field.classList.add("error");
    let id = field.id;
    if (!id)
        return;
    let message = document.getElementById("error-for-" + id);
    if (!message) {
        message = document.createElement("span");
        message.className = "error";
        message.id = "error-for-" + id;
        field.parentNode.insertBefore(message, field.nextSibling);
    }
    message.innerHTML = error;
}