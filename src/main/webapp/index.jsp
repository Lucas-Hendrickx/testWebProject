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
        <script src="extra/main.js"></script>
    </head>
    <body>

<!----- Navigation --------------------------------------------------------------------------------------------------->

        <nav class="nav-list">
            <ul class="w3-sidebar w3-bar-block w3-collapse w3-card w3-dark-grey" style="width:200px;" id="mySidebar">
                <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>

                <li class="tablinks"><a class="active" href="Controller?command=Open_Index">Home</a></li>
                <li class="tablinks"><a href="Controller?command=Open_Form">Register</a></li>

                <c:if test="${registered.role=='ADMIN' || registered.role=='GUARDIAN'}">
                    <li class="tablinks"><a href="Controller?command=Open_Overview">Overview</a></li>
                    <li class="tablinks"><a href="Controller?command=Open_CoronaTest">CoronaTest</a></li>
                </c:if>

                <li class="tablinks"><a href="Controller?command=Open_Event">Events</a></li>
            </ul>
        </nav>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <jsp:include page="extra/title.jsp">
                <jsp:param name="title" value="Home" />
            </jsp:include>

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

<!------------- Errors / Success ------------------------------------------------------------------------------------->

                <c:if test = "${not empty result}">
                    <div class="alert-danger">
                        <ul>
                            <c:forEach items = "${result}" var="error">
                                <li><c:out value='${error}'/></li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <c:if test="${success!=null}">
                    <div class="alert-feedback">
                        <p>${success}</p>
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