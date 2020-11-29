<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Details</title>
        <link rel="stylesheet" href="extra/style.css">
        <link rel="stylesheet" href="extra/w3.css">
        <script src="extra/script.js"></script>
    </head>
    <body>

<!----- Navigatie ---------------------------------------------------------------------------------------------------->

        <%--
                A header from "header.jsp"
        --%>

        <jsp:include page="extra/header.jsp"/>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <jsp:include page="extra/title.jsp">
                <jsp:param name="title" value="Details" />
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

<!------------- Logged In -------------------------------------------------------------------------------------------->

                <c:if test = "${not empty registered.role}">
                    <c:if test = "${empty allUsersOfEvent}">

                        <h2>There are no users registered at the event</h2>

                    </c:if>
                    <c:if test = "${not empty allUsersOfEvent}">

                        <h2>Event: <c:out value="${eventname}"/> with <c:out value="${amountOfUsers}"/>/<c:out value="${amountOfPeopleAllowed}"/> people</h2>

                        <table class='w3-table-all'>
                            <tr>
                                <th>Email</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Group</th>
                                <th>Remove</th>
                            </tr>
                            <c:forEach var="user" items="${allUsersOfEvent}">
                                <tr>
                                    <td><c:out value="${user.registeredEmail}"/></td>
                                    <td><c:out value="${user.firstname}"/></td>
                                    <td><c:out value="${user.lastname}"/></td>
                                    <td><c:out value="${user.usergroup}"/></td>
                                    <c:if test = "${registered.role == 'ADMIN' || user.registeredEmail == email}">
                                        <td><a href="Controller?command=Function_RemoveUserOfEvent&eventId=<c:out value="${eventId}"/>&registeredEmail=<c:out value="${user.registeredEmail}"/>&firstname=<c:out value="${user.firstname}"/>&lastname=<c:out value="${user.lastname}"/>">Remove</a></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </table>

                    </c:if>
                </c:if>

<!------------- Logged In As Guardian ----------------------------------------------------------------------------------->

                <c:if test = "${registered.role == 'GUARDIAN'}">

                    <h2>Form for adding a user to the event.</h2>

                    <form method="POST" action="Controller?command=Function_AddUserToEvent&eventId=${eventId}" novalidate="novalidate">

                        <p><label for="user">User</label>
                            <select class="w3-select" id='user' name="user" required >
                                <option value="" disabled selected>Select user</option>

                                <c:forEach var="user" items="${allUsersWithEmail}">
                                    <option value="<c:out value="${user}"/>"><c:out value="${user.firstname}"/> <c:out value="${user.lastname}"/></option>
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