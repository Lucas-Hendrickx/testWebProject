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
                    A title from "title.jsp" with value "Contact Register"
            --%>

            <jsp:include page="title.jsp">
                <jsp:param name="title" value="Contact Register" />
            </jsp:include>

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

<!------------- Errors ----------------------------------------------------------------------------------------------->

                <%--
                        A list with all errors with destination "contactregister.jsp"
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


<!------------- Logged In -------------------------------------------------------------------------------------------->

                <%--
                        If you are logged in, you see an overview of contact
                        for the main event.
                        There is also a form to that lets you attend the event.

                        Controller => OverviewUsersInEvent => OverviewUsersWithEmail
                --%>

                <c:if test = "${not empty role}">
                    <c:if test = "${empty usersInEvent}">

                        <h2>There are no users registered in the event</h2>

                    </c:if>
                    <c:if test = "${not empty usersInEvent}">

                        <h2>Event: ${event.name} with ${amountOfUsers}/${event.amountOfPeopleAllowed} people</h2>

                        <table class='w3-table-all'>
                            <tr>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Group</th>
                            </tr>
                            <c:forEach var="user" items="${usersInEvent}">
                                <tr>
                                    <td>${user.firstname}</td>
                                    <td>${user.lastname}</td>
                                    <td>${user.usergroup}</td>
                                </tr>
                            </c:forEach>
                        </table>

                    </c:if>

                    <h2>Form for adding a user to the contact register.</h2>

                    <form method="POST" action="Controller?command=AddUserToEvent&eventId=1" novalidate="novalidate">

                        <p><label for="user">User</label>
                            <select class="w3-select" id='user' name="user" required >
                                <option value="" disabled selected>Select user</option>

                                <c:forEach var="user" items="${allUsersWithEmail}">
                                    <option value="${user}">${user.firstname} ${user.lastname}</option>
                                </c:forEach>

                            </select>
                        <p><input type="submit" id="addUserToEvent" value="Sign Up"></p>
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
