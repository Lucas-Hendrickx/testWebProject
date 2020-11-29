<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Home</title>
        <link rel="stylesheet" href="extra/style.css">
        <link rel="stylesheet" href="extra/w3.css">
        <script src="extra/script.js"></script>
    </head>
    <body>

<!----- Navigation --------------------------------------------------------------------------------------------------->

        <jsp:include page="extra/header.jsp"/>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <jsp:include page="extra/title.jsp">
                <jsp:param name="title" value="Home" />
            </jsp:include>

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

<!------------- Errors ----------------------------------------------------------------------------------------------->

                <c:if test = "${not empty result}">
                    <div class="alert-danger">
                        <ul>
                            <c:forEach items = "${result}" var="error">
                                <li><c:out value='${error}'/></li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

<!------------- Not Logged In ---------------------------------------------------------------------------------------->

                <c:if test = "${empty registered.role}">

                    <h2>Welcome to our Chiro Website!</h2>

                    <p>
                        If you are new here you can go to the Register tab to create an account.
                        When you have made an account you can log in on this form.
                    </p>

                    <h2>Log in form</h2>

                    <form method="POST" action="Controller?command=Function_Login" novalidate="novalidate">

                        <p><label for="email">Email</label>
                            <input type="email" id="email" name="email" value="<c:out value='${emailReturn}'/>" required > </p>

                        <p><label for="password">Password</label>
                            <input type="password" id="password"  name="password" required > </p>

                        <p><input type="submit" id="login" value="Log in"></p>
                    </form>

                </c:if>

<!------------- Logged In -------------------------------------------------------------------------------------------->

                <c:if test = "${not empty registered.role}">

                    <h2>Welcome <c:out value="${firstname}"/></h2>

                    <form method="POST" action="Controller?command=Function_Logout" novalidate="novalidate">
                        <p><input type="submit" id="logout" value="Log out"></p>
                    </form>

                    <h2>If you would like to change password:</h2>

                    <form method="POST" action="Controller?command=Function_ChangePassword" novalidate="novalidate">

                        <p><label for="changePasswordA">Password</label>
                            <input type="password" id="changePasswordA" name="changePasswordA" required > </p>

                        <p><label for="changePasswordB">Repeat Password</label>
                            <input type="password" id="changePasswordB"  name="changePasswordB" required > </p>

                        <p><input type="submit" id="Change" value="Change Password"></p>
                    </form>

                </c:if>

<!------------- Footer ----------------------------------------------------------------------------------------------->

                <footer>
                    &copy; Webontwikkeling 3, UC Leuven-Limburg
                </footer>

            </div>
        </div>
    </body>
</html>