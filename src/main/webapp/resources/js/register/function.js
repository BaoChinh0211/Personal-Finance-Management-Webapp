
function checkEmailValid(email) {
    var emailRegex = /^[a-zA-Z0-9]+([a-zA-Z0-9.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)+$/;
    var emailSplit = email.split('@', 2);

    if (email.trim() === "") {
        return 3; // Empty email
    } else if (!email.match(emailRegex)) {
        return 2; // Invalid email format
    } else if (!(emailSplit[0].length >= 6 && emailSplit[0].length <= 30)) {
        return 1; // Invalid email username length
    } else {
        return 0; // Valid email
    }
}


var emailErrorDiv = document.getElementById("emailError");
var sendCodeMessageDiv = document.getElementById("sendCodeMessage");

function submitSendCodeForm() {
    var sendCodeEmail = document.getElementById("email").value;

    // Reset error messages
    emailErrorDiv.innerText = "";
    sendCodeMessageDiv.innerText = "";
    sendCodeMessageDiv.style.display = "block";
    sendCodeMessageDiv.classList.remove("alert-danger");
    sendCodeMessageDiv.classList.remove("alert-success");

    // Client-side validation
    if (checkEmailValid(sendCodeEmail) === 3) {
        // Show error message for empty email
        emailErrorDiv.innerText = "Please enter your email.";
        emailErrorDiv.style.display = "block";

    } else if (checkEmailValid(sendCodeEmail) === 2) {
        // Show error message for invalid email
        emailErrorDiv.innerText = "Please enter valid email.";
        emailErrorDiv.style.display = "block";

    } else if (checkEmailValid(sendCodeEmail) === 1) {
        emailErrorDiv.innerText = "Please enter email username length from 6 to 30.";
        emailErrorDiv.style.display = "block";
    } else if (checkEmailExistence(sendCodeEmail) === "true") {
        emailErrorDiv.innerText = "Email is already in use, please try another email.";
        emailErrorDiv.style.display = "block";
    } else {
        $.ajax({
            type: "POST",
            url: "/PersonalFinanceManagementWebapp/register/sendCode",
            data: {sendCodeEmail: sendCodeEmail},
            success: function (response) {
                // Display success or error message below the button
                sendCodeMessageDiv.innerText = response;
                sendCodeMessageDiv.classList.remove("alert-danger");
                sendCodeMessageDiv.classList.add(response.includes("successfully") ? "alert-success" : "alert-danger");
                sendCodeMessageDiv.style.display = "block";
            },
            error: function (error) {
                // Handle error, display the error message in sendCodeMessage
                sendCodeMessageDiv.innerText = "Error sending code: " + error.responseText;
                sendCodeMessageDiv.classList.remove("alert-success");
                sendCodeMessageDiv.classList.add("alert-danger");
                sendCodeMessageDiv.style.display = "block";
            }
        });

        // Clear email error if email is valid
        emailErrorDiv.innerText = "";
        emailErrorDiv.style.display = "none";
    }


}
function checkEmailExistence(email) {
    var isEmailExist = "";
    // Gửi yêu cầu kiểm tra email tồn tại
    $.ajax({
        type: "POST",
        url: "/PersonalFinanceManagementWebapp/register/checkEmailExistence",
        data: {email: email},
        success: function (response) {
            isEmailExist = response;
        },
        error: function (error) {
            console.log("Error checking email existence: " + error.responseText);
            isEmailExist = false;
        }
    });
    return isEmailExist;
}
