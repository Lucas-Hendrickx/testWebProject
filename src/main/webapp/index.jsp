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

        <%--
                A header from "header.jsp"
        --%>

        <jsp:include page="header.jsp"/>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <%--
                    A title from "title.jsp" with value "Home"
            --%>

            <jsp:include page="title.jsp">
                <jsp:param name="title" value="Home" />
            </jsp:include>

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

<!------------- Errors ----------------------------------------------------------------------------------------------->

                <%--
                        A list with all errors with destination "index.jsp"
                --%>

                <c:if test = "${not empty result}">
                    <div class="alert-danger">
                        <ul>
                            <c:forEach items = "${result}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

<!------------- Not Logged In ---------------------------------------------------------------------------------------->

                <%--
                        A form to login as a registered.
                        Controller => Login
                --%>

                <c:if test = "${empty role}">

                    <h2>Welcome to our Chiro Website!</h2>

                    <p>
                        If you are new here you can go to the Register tab to create an account.
                        When you have made an account you can log in on this form.
                    </p>

                    <h2>Log in form</h2>

                    <form method="POST" action="Controller?command=Login" novalidate="novalidate">

                        <p><label for="email">Email</label>
                            <input type="email" id="email" name="email" value="${emailReturn}" required > </p>

                        <p><label for="password">Password</label>
                            <input type="password" id="password"  name="password" required > </p>

                        <p><input type="submit" id="login" value="Log in"></p>
                    </form>

                </c:if>

<!------------- Header for Logged In --------------------------------------------------------------------------------->

                <%--
                        A welcome text with your firstname
                        A butten so you can logout
                        Controller => Logout
                --%>

                <c:if test = "${not empty role}">

                    <h2>Welcome ${firstname}!</h2>

                    <form method="POST" action="Controller?command=Logout" novalidate="novalidate">
                        <p><input type="submit" id="logout" value="Log out"></p>
                    </form>

                </c:if>

<!------------- Logged In As Guardian -------------------------------------------------------------------------------->

                <%--
                        An overview of users with your email
                        Controller => OverviewUsersWithEmail
                --%>

                <c:if test = "${role == 'Guardian'}">

                    <c:if test = "${empty allUsersWithEmail}">

                        <p>There are no users on this account</p>

                    </c:if>
                    <c:if test = "${not empty allUsersWithEmail}">

                        <table class='w3-table-all'>
                            <tr>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Group</th>
                            </tr>
                            <c:forEach var="user" items="${allUsersWithEmail}">
                                <tr>
                                    <td>${user.firstname}</td>
                                    <td>${user.lastname}</td>
                                    <td>${user.usergroup}</td>
                                </tr>
                            </c:forEach>
                        </table>

                    </c:if>
                </c:if>

<!------------- Logged In As Admin ----------------------------------------------------------------------------------->

                <%--
                        An overview of users
                        Controller => OverviewAllUsers
                --%>

                <c:if test = "${role == 'Admin'}">

                    <p>Admin</p>

                    <c:if test = "${empty allUsers}">

                        <p>There are no users in the database</p>

                    </c:if>
                    <c:if test = "${not empty allUsers}">

                    <table class='w3-table-all'>
                        <tr>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Group</th>
                        </tr>
                        <c:forEach var="user" items="${allUsers}">
                            <tr>
                                <td>${user.firstname}</td>
                                <td>${user.lastname}</td>
                                <td>${user.usergroup}</td>
                            </tr>
                        </c:forEach>
                    </table>

                    </c:if>
                </c:if>

<!------------- Footer ----------------------------------------------------------------------------------------------->

                <footer>
                    &copy; Webontwikkeling 3, UC Leuven-Limburg
                </footer>

            </div>
        </div>
    </body>
</html>