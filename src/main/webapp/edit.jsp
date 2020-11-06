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

<!----- Navigatie ---------------------------------------------------------------------------------------------------->

        <%--
                A header from "header.jsp"
        --%>

        <jsp:include page="header.jsp"/>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <%--
                    A title from "title.jsp" with value "Edit"
             --%>

            <jsp:include page="title.jsp">
                <jsp:param name="title" value="Edit" />
            </jsp:include>

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

<!------------- Errors ----------------------------------------------------------------------------------------------->

                <%--
                        A list with all errors with destination "edit.jsp"
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
                        If you are not logged in, you will get plain text thats says you need to log in.
                --%>

                <c:if test = "${empty role}">

                    <h2>
                        You first need to login if you want to edit the data.
                    </h2>

                </c:if>

<!------------- Logged In As Guardian -------------------------------------------------------------------------------->

                <%--
                        A form to remove a user with your email
                        Controller => RemoveUser
                --%>

                <c:if test = "${role == 'Guardian'}">

                    <h2>Guardian</h2>
                    <h2>If you would like to remove a user:</h2>

                    <form method="POST" action="Controller?command=RemoveUser" novalidate="novalidate">

                        <p><label for="firstname">Firstname</label>
                            <input type="text" id="firstname" name="firstname" value="${firstnameReturn}" required > </p>

                        <p><label for="lastname">Lastname</label>
                            <input type="text" id="lastname" name="lastname" value="${lastnameReturn}" required > </p>

                        <p><input type="submit" id="userRemove" value="Remove User"></p>
                    </form>


                </c:if>

<!------------- Logged In As Admin ----------------------------------------------------------------------------------->

                <%--
                        A form to remove registered persons.
                        Controller => RemoveRegistered
                --%>

                <c:if test = "${role == 'Admin'}">

                    <h2>If you would like to remove a registered:</h2>

                    <form method="POST" action="Controller?command=RemoveRegistered" novalidate="novalidate">

                        <p><label for="email">Email</label>
                            <input type="email" id="email" name="email" value="${emailReturn}" required ></p>

                        <p><input type="submit" id="registeredRemove" value="Remove Person"></p>
                    </form>

                </c:if>

<!------------- Logged In -------------------------------------------------------------------------------------------->

                <%--
                        A form to change your password
                        Controller => ChangeRegisteredPassword
                --%>

                <c:if test = "${not empty role}">

                    <h2>If you would like to change password:</h2>

                    <form method="POST" action="Controller?command=ChangeRegisteredPassword" novalidate="novalidate">

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
