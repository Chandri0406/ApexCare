<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createStyle.css"> <!-- Link to your CSS file -->
</head>
<body>

<div class="bg"> 
    <h2 id="lgLabel">CREATE A USER</h2>
    <form action="createUser" method="post">

        <div>
            <label for="firstName" class="lbl">First Name: </label> <br>
            <input type="text" name="firstName" class="inputBox" required />
        </div>

        <div>
            <label for="lastName" class="lbl">Last Name: </label> <br>
            <input type="text" name="lastName" class="inputBox" required />
        </div>

        <div>
            <label for="phone" class="lbl">Phone: </label> <br>
            <input type="text" name="phone" class="inputBox" required />
        </div>

        <div>
            <label for="email" class="lbl">Email: </label> <br>
            <input type="email" name="email" class="inputBox" required id="email" />
        </div>

        <div>
            <label for="confirmEmail" class="lbl">Confirm Email: </label> <br>
            <input type="email" name="confirmEmail" required id="confirmEmail" class="inputBox" /><br>
            <span class="Validation error-message" id="emailError"></span> 
        </div>

        <div>
            <label for="address" class="lbl">Address: </label> <br>
            <input type="text" name="address" required class="inputBox" />
        </div>

        <div>
            <label for="username" class="lbl">Username: </label> <br>
            <input type="text" name="username" required class="inputBox" />
        </div>

        <div>
            <label for="password" class="lbl">Password: </label> <br>
            <input type="password" name="password" required id="password" class="inputBox" />
            <input type="checkbox" onclick="showPassword()">Show Password<br>
        </div>

        <div>
            <label for="confirmPassword" class="lbl">Confirm Password: </label> <br>
            <input type="password" name="confirmPassword" required id="confirmPassword" class="inputBox" />
            <input type="checkbox" onclick="showPassword()">Show Password<br>
            <span class="Validation error-message" id="passwordError"></span>
        </div>

        <button type="submit" class="btn">Sign Up</button>
    </form>
</div>

<script>
    // Real-time validation for email and password match
    document.getElementById('confirmEmail').addEventListener('input', validateEmail);
    document.getElementById('confirmPassword').addEventListener('input', validatePassword);

    function validateEmail() {
        const email = document.getElementById('email').value;
        const confirmEmail = document.getElementById('confirmEmail').value;
        const emailError = document.getElementById('emailError');

        if (email !== confirmEmail) {
            document.getElementById('confirmEmail').classList.add('input-error');
            emailError.textContent = "Emails do not match!";
        } else {
            document.getElementById('confirmEmail').classList.remove('input-error');
            emailError.textContent = "";
        }
    }

    function validatePassword() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const passwordError = document.getElementById('passwordError');

        if (password !== confirmPassword) {
            document.getElementById('confirmPassword').classList.add('input-error');
            passwordError.textContent = "Passwords do not match!";
        } else {
            document.getElementById('confirmPassword').classList.remove('input-error');
            passwordError.textContent = "";
        }
    }
    
    function showPassword() {
    var x = document.getElementById("myInput");
        if (x.type === "password") {
          x.type = "text";
        } else {
          x.type = "password";
        }
      } 

    // Prevent form submission if there are validation errors
    document.getElementById('signupForm').addEventListener('submit', function (event) {
        validateEmail();
        validatePassword();

        const emailError = document.getElementById('emailError').textContent;
        const passwordError = document.getElementById('passwordError').textContent;

        if (emailError || passwordError) {
            event.preventDefault(); // Stop form submission
        }
    });
</script>

</body>
</html>
