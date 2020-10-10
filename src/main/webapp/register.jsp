<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Sign Up</title>
        <link rel="stylesheet" href="extra/style.css">
        <link rel="stylesheet" href="extra/w3.css">
        <script src="extra/script.js"></script>
    </head>
    <body>

<!----- Navigatie ---------------------------------------------------------------------------------------------------->

        <ul class="w3-sidebar w3-bar-block w3-collapse w3-card w3-dark-grey" style="width:200px;" id="mySidebar">
            <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="Controller?command=Overview">Overview</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>

<!-------------------------------------------------------------------------------------------------------------------->

        <div class="w3-main" style="margin-left:200px">

<!--------- Titel ---------------------------------------------------------------------------------------------------->

            <div class="w3-dark-grey">
                <button class="w3-button w3-dark-grey w3-xlarge w3-hide-large" onclick="w3_open()">&#9776;</button>
                <div class="w3-container">
                    <h2>Register</h2>
                </div>
            </div>

<!--------- Inhoud Pagina -------------------------------------------------------------------------------------------->

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

                <c:if test = "${not empty result}">
                    <div class="alert-danger">
                        <ul>
                            <c:forEach items = "${result}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <form method="POST" action="Controller?command=Register" novalidate="novalidate">
                    <!-- novalidate in order to be able to run tests correctly -->
                    <p><label for="userid">User id</label>
                        <input type="text" id="userid" name="userid" value="${idPre}" required > </p>
                    <p><label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" value="${fnPre}" required > </p>
                    <p><label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" value="${lnPre}" required > </p>
                    <p><label for="email">Email</label>
                        <input type="email" id="email" name="email" value="${emPre}" required ></p>
                    <p><label for="password">Password</label>
                        <input type="password" id="password"  name="password" value="${pwPre}" required > </p>

                    <p><input type="submit" id="signUp" value="Sign Up"></p>

                </form>

<!-------------------------------------------------------------------------------------------------------------------->

                <footer>
                    &copy; Webontwikkeling 3, UC Leuven-Limburg
                </footer>

            </div>
        </div>
    </body>
</html>
