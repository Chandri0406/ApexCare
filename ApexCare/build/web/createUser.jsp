<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createStyle.css"> <!-- Link to your CSS file -->
</head>
<body>

    <div id="bg"> 
        <h2 id="lgLabel">CREATE A USER</h2>
        
        <form action="${pageContext.request.contextPath}/createUser" method="post" id="signupForm">

            <div class="fields">
                <label for="firstName" class="lbl">First Name: </label>
                <input type="text" name="firstName" class="inputBox" required />
            </div>

            <div class="fields">
                <label for="lastName" class="lbl">Last Name: </label>
                <input type="text" name="lastName" class="inputBox" required />
            </div>

            <div class="fields">
                <label for="phone" class="lbl">Phone: </label>
                <input type="text" name="phone" class="inputBox" required />
            </div>

            <div class="fields">
                <label for="email" class="lbl">Email: </label>
                <input type="email" name="email" class="inputBox" required id="email" />
            </div>

            <div class="fields">
                <label for="confirmEmail" class="lbl">Confirm Email: </label>
                <input type="email" name="confirmEmail" required id="confirmEmail" class="inputBox" /><br>
                <span class="Validation error-message" id="emailError"></span> 
            </div>

            <div class="fields">
                <label for="address" class="lbl">Address: </label>
                <input type="text" name="address" required class="inputBox" />
            </div>

            <div class="fields">
                <label for="username" class="lbl">Username: </label>
                <input type="text" name="username" required class="inputBox" />
            </div>

            <div class="fields">
                <label for="password" class="lbl">Password: </label> 
                <input type="password" name="password" required id="password" class="inputBox" />
                <input type="checkbox" onclick="showPassword()">Show Password<br>
            </div>

            <div class="fields">
                <label for="confirmPassword" class="lbl">Confirm Password: </label>
                <input type="password" name="confirmPassword" required id="confirmPassword" class="inputBox" />
                <input type="checkbox" onclick="showPasswordCon()">Show Password<br>
                <span class="Validation error-message" id="passwordError"></span>
            </div>

            <button type="submit" id="btn">Sign Up</button>
        </form>
    </div>

    <!-- Display alerts based on success or error message -->
    <script>
        // Check if there's a success or error message from the server
        const successMessage = '<%= request.getAttribute("successMessage") != null ? request.getAttribute("successMessage") : "" %>';
        const errorMessage = '<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>';

        if (successMessage) {
            alert(successMessage);
        }

        if (errorMessage) {
            alert(errorMessage);
        }

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
            var x = document.getElementById("password");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }

        function showPasswordCon() {
            var x = document.getElementById("confirmPassword")
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }
    </script>

</body>
</html>
