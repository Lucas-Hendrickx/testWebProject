<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Corona Test</title>
        <link rel="stylesheet" href="extra/style.css">
        <link rel="stylesheet" href="extra/w3.css">
        <script src="extra/script.js"></script>
        <script src="extra/main.js"></script>
    </head>
    <body>

<!----- Navigatie ---------------------------------------------------------------------------------------------------->

        <nav class="nav-list">
            <ul class="w3-sidebar w3-bar-block w3-collapse w3-card w3-dark-grey" style="width:200px;" id="mySidebar">
                <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>

                <li class="tablinks"><a href="Controller?command=Open_Index">Home</a></li>
                <li class="tablinks"><a href="Controller?command=Open_Form">Register</a></li>

                <c:if test="${registered.role=='ADMIN' || registered.role=='GUARDIAN'}">
                    <li class="tablinks"><a href="Controller?command=Open_Overview">Overview</a></li>
                    <li class="tablinks"><a class="active" href="Controller?command=Open_CoronaTest">CoronaTest</a></li>
                </c:if>

                <li class="tablinks"><a href="Controller?command=Open_Event">Events</a></li>
            </ul>
        </nav>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <jsp:include page="extra/title.jsp">
                <jsp:param name="title" value="Corona Test" />
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

<!------------- Filter ----------------------------------------------------------------------------------------------->

                <div class="filter">
                    <form method="POST" action="Controller?command=Function_Filter" novalidate="novalidate">
                        <p><label for="fromDate">From</label>
                            <input class="w3-input w3-border w3-round" type="date" id="fromDate" name="fromDate" value="<c:out value='${fromDateReturn}'/>" required></p>

                        <p><label for="untilDate">Until</label>
                            <input class="w3-input w3-border w3-round" type="date" id="untilDate" name="untilDate" value="<c:out value='${untilDateReturn}'/>" required></p>

                        <c:if test = "${registered.role == 'ADMIN'}">
                            <p><label for="registered">User</label>
                                <select class="w3-select" id='registered' name="registered">
                                    <option value="" disabled selected>Select Guardian</option>
                                    <c:forEach var="registered" items="${allRegistered}">
                                        <option value="<c:out value="${registered.email}"/>"><c:out value="${registered.firstname}"/> <c:out value="${registered.lastname}"/></option>
                                    </c:forEach>
                                </select></p>
                        </c:if>

                        <p><input type="submit" id="filterDate" value="filter"></p>
                    </form>
                    <form method="POST" action="Controller?command=Open_CoronaTest" novalidate="novalidate">
                        <p><input type="submit" id="removeFilter" value="clear filter"></p>
                    </form>
                </div>

<!------------- Logged In As Guardian -------------------------------------------------------------------------------->

                <c:if test = "${registered.role == 'GUARDIAN'}">

                    <c:if test = "${empty allCoronaTestsOfRegistered}">

                        <h2>Your users have no corona tests in the database.</h2>

                    </c:if>
                    <c:if test = "${not empty allCoronaTestsOfRegistered}">

                        <h2>All corona tests of your users:</h2>

                        <table class='w3-table-all'>
                            <tr>
                                <th>Date</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Group</th>
                            </tr>
                            <c:forEach var="key" items="${allCoronaTestsOfRegistered.keySet()}">
                                <tr id="contactlist">
                                    <td><c:out value="${key}"/></td>
                                    <td><c:out value="${allCoronaTestsOfRegistered.get(key).firstname}"/></td>
                                    <td><c:out value="${allCoronaTestsOfRegistered.get(key).lastname}"/></td>
                                    <td><c:out value="${allCoronaTestsOfRegistered.get(key).usergroup}"/></td>
                                </tr>
                            </c:forEach>
                        </table>

                    </c:if>

                    <form method="POST" action="Controller?command=Create_CoronaTest" novalidate="novalidate">

                        <p><label for="coronatestDate">Date</label>
                            <input class="w3-input w3-border w3-round" type="date" id="coronatestDate" name="date" value="<c:out value="${dateReturn}"/>" required> </p>

                        <p><label for="coronatestTime">Time</label>
                            <input class="w3-input w3-border w3-round" type="time" id="coronatestTime" name="time" value="<c:out value="${timeReturn}"/>}" required> </p>

                        <p><label for="user">User</label>
                            <select class="w3-select" id='user' name="user" required >
                                <option value="" disabled selected>Select user</option>

                                <c:forEach var="user" items="${allUsersWithEmail}">
                                    <option value="<c:out value="${user}"/>"><c:out value="${user.firstname}"/> <c:out value="${user.lastname}"/></option>
                                </c:forEach>

                            </select>
                        </p>

                        <p><input type="submit" id="createCoronaTest" value="Sign Up"></p>
                    </form>

                </c:if>

<!------------- Logged In As Admin ----------------------------------------------------------------------------------->

                <c:if test = "${registered.role == 'ADMIN'}">

                    <c:if test = "${empty allCoronaTests}">

                        <h2>Your users have no corona tests in the database.</h2>

                    </c:if>
                    <c:if test = "${not empty allCoronaTests}">

                        <h2>All corona tests of all users:</h2>
                        <table class='w3-table-all'>
                            <tr>
                                <th>Date</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Group</th>
                                <th>Contact</th>
                            </tr>
                            <c:forEach var="key" items="${allCoronaTests.keySet()}">
                                <tr>
                                    <td><c:out value="${key}"/></td>
                                    <td><c:out value="${allCoronaTests.get(key).firstname}"/></td>
                                    <td><c:out value="${allCoronaTests.get(key).lastname}"/></td>
                                    <td><c:out value="${allCoronaTests.get(key).usergroup}"/></td>
                                    <td><a href="Controller?command=Open_Contact&userid=<c:out value="${allCoronaTests.get(key).userId}"/>&date=<c:out value="${key}"/>">See Contacts</a></td>
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