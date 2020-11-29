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
    </head>
    <body>

<!----- Navigatie ---------------------------------------------------------------------------------------------------->

        <jsp:include page="extra/header.jsp"/>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <jsp:include page="extra/title.jsp">
                <jsp:param name="title" value="Corona Test" />
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

<!------------- Logged In As Admin ----------------------------------------------------------------------------------->

                <c:if test = "${registered.role == 'ADMIN'}">

                    <c:if test = "${empty allContactsOfUser}">

                        <h2>Nobody got in contact with the the user who tested positive</h2>

                    </c:if>
                    <c:if test = "${not empty allContactsOfUser}">

                        <h2>All users who got in contact with the user who tested positive:</h2>
                        <table class='w3-table-all'>
                            <tr>
                                <th>Name Guardian</th>
                                <th>Email</th>
                                <th>GSM number</th>
                                <th>Name kid</th>
                                <th>Group</th>
                            </tr>
                            <c:forEach var="user" items="${allContactsOfUser.keySet()}">
                                <tr>
                                    <td><c:out value="${allContactsOfUser.get(user).firstname}"/> <c:out value="${allContactsOfUser.get(user).lastname}"/></td>
                                    <td><c:out value='${user.registeredEmail}'/></td>
                                    <td><c:out value="${allContactsOfUser.get(user).gsmnumber}"/></td>
                                    <td><c:out value='${user.firstname}'/> <c:out value='${user.lastname}'/></td>
                                    <td><c:out value='${user.usergroup}'/></td>
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