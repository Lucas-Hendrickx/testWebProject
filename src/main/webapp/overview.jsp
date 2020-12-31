<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Overview</title>
        <link rel="stylesheet" href="extra/style.css">
        <link rel="stylesheet" href="extra/w3.css">
        <script src="extra/script.js"></script>
    </head>
    <body>

<!----- Navigatie ---------------------------------------------------------------------------------------------------->

        <nav class="nav-list">
            <ul class="w3-sidebar w3-bar-block w3-collapse w3-card w3-dark-grey" style="width:200px;" id="mySidebar">
                <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>

                <li class="tablinks"><a href="Controller?command=Open_Index">Home</a></li>
                <li class="tablinks"><a href="Controller?command=Open_Form">Register</a></li>

                <c:if test="${registered.role=='ADMIN' || registered.role=='GUARDIAN'}">
                    <li class="tablinks"><a class="active" href="Controller?command=Open_Overview">Overview</a></li>
                    <li class="tablinks"><a href="Controller?command=Open_CoronaTest">CoronaTest</a></li>
                </c:if>

                <li class="tablinks"><a href="Controller?command=Open_Event">Events</a></li>
            </ul>
        </nav>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <jsp:include page="extra/title.jsp">
                <jsp:param name="title" value="Overview" />
            </jsp:include>

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

<!------------- Success ---------------------------------------------------------------------------------------------->

                <c:if test="${success!=null}">
                    <div class="alert-feedback">
                        <p>${success}</p>
                    </div>
                </c:if>

<!------------- Logged In As Guardian -------------------------------------------------------------------------------->

                <c:if test = "${registered.role == 'GUARDIAN'}">
                    <c:if test = "${empty allUsersWithEmail}">

                        <h2>There are no users on this account</h2>

                    </c:if>
                    <c:if test = "${not empty allUsersWithEmail}">

                        <table class='w3-table-all'>
                            <tr>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Group</th>
                                <th>Remove</th>
                            </tr>
                            <c:forEach var="user" items="${allUsersWithEmail}">
                                <tr>
                                    <td><c:out value="${user.firstname}"/></td>
                                    <td><c:out value="${user.lastname}"/></td>
                                    <td><c:out value="${user.usergroup}"/></td>
                                    <td><a href="Controller?command=Remove_User&registeredEmail=<c:out value="${user.registeredEmail}"/>&firstname=<c:out value="${user.firstname}"/>&lastname=<c:out value="${user.lastname}"/>">Remove</a></td>
                                </tr>
                            </c:forEach>
                        </table>

                    </c:if>
                </c:if>

<!------------- Logged In As Admin ----------------------------------------------------------------------------------->

                <c:if test = "${registered.role == 'ADMIN'}">
                    <c:if test = "${empty allRegistered}">

                        <h2>There are no registered persons in the database</h2>

                    </c:if>
                    <c:if test = "${not empty allRegistered}">

                        <h2>All Registered persons in the database:</h2>
                        <table class='w3-table-all'>
                            <tr>
                                <th>Email</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Gsm number</th>
                                <th>Remove</th>
                            </tr>
                            <c:forEach var="registered" items="${allRegistered}">
                                <tr>
                                    <td><c:out value="${registered.email}"/></td>
                                    <td><c:out value="${registered.firstname}"/></td>
                                    <td><c:out value="${registered.lastname}"/></td>
                                    <td><c:out value="${registered.gsmnumber}"/></td>
                                    <td><a href="Controller?command=Remove_Registered&email=<c:out value="${registered.email}"/>">Remove</a></td>
                                </tr>
                            </c:forEach>
                        </table>

                    </c:if>

                    <c:if test = "${empty allUsers}">

                        <h2>There are no users in the database</h2>

                    </c:if>
                    <c:if test = "${not empty allUsers}">

                        <h2>All users in the database:</h2>
                        <table class='w3-table-all'>
                            <tr>
                                <th>Email</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Group</th>
                                <th>Remove</th>
                            </tr>
                            <c:forEach var="user" items="${allUsers}">
                                <tr>
                                    <td><c:out value="${user.registeredEmail}"/></td>
                                    <td><c:out value="${user.firstname}"/></td>
                                    <td><c:out value="${user.lastname}"/></td>
                                    <td><c:out value="${user.usergroup}"/></td>
                                    <td><a href="Controller?command=Remove_User&registeredEmail=<c:out value="${user.registeredEmail}"/>&firstname=<c:out value="${user.firstname}"/>&lastname=<c:out value="${user.lastname}"/>">Remove</a></td>
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