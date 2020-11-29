<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Event</title>
        <link rel="stylesheet" href="extra/style.css">
        <link rel="stylesheet" href="extra/w3.css">
        <script src="extra/script.js"></script>
    </head>
    <body>

<!----- Navigatie ---------------------------------------------------------------------------------------------------->

        <jsp:include page="extra/header.jsp"/>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <jsp:include page="extra/title.jsp">
                <jsp:param name="title" value="Events" />
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

<!------------- Events ----------------------------------------------------------------------------------------------->

                <c:if test = "${empty allEvents}">

                    <h2>There are no events in the database</h2>

                </c:if>
                <c:if test = "${not empty allEvents}">

                    <h2>All Events:</h2>
                    <table class='w3-table-all'>
                        <tr>
                            <th>Name</th>
                            <th>Date</th>
                            <c:if test = "${not empty role}">
                                <th>Duration</th>
                                <th>Amount Of People Allowed</th>
                                <th>Corona</th>
                                <th>Details</th>
                            </c:if>
                        </tr>
                        <c:forEach var="event" items="${allEvents}">
                            <tr>
                                <td><c:out value="${event.name}"/></td>
                                <td><c:out value="${event.getStringDate()}"/></td>
                                <c:if test = "${not empty role}">
                                    <td><c:out value="${event.duration}"/></td>
                                    <td><c:out value="${event.amountOfPeopleAllowed}"/></td>
                                    <c:if test = "${eventidsWithCorona.contains(event.eventId)}">
                                        <td>Danger</td>
                                    </c:if>
                                    <c:if test = "${!eventidsWithCorona.contains(event.eventId)}">
                                        <td>Safe</td>
                                    </c:if>
                                    <td><a href="Controller?command=Open_Details&eventId=${event.eventId}">Details</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>

                </c:if>

<!------------- Footer ----------------------------------------------------------------------------------------------->

                <footer>
                    &copy; Webontwikkeling 3, UC Leuven-Limburg
                </footer>

            </div>
        </div>
    </body>
</html>