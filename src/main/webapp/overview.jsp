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
        <%--
                A header from "header.jsp"
        --%>

        <jsp:include page="header.jsp"/>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <%--
                    A title from "title.jsp" with value "Overview"
            --%>

            <jsp:include page="title.jsp">
                <jsp:param name="title" value="Overview" />
            </jsp:include>

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">


<!------------- Not Logged In ---------------------------------------------------------------------------------------->

                <%--
                        A form to login as a registered.
                        Controller => Login
                --%>

                <c:if test = "${empty role}">

                    <h2>You need to be logged in as Admin to see all registered persons.</h2>

                </c:if>

<!------------- Logged In As Admin ----------------------------------------------------------------------------------->

                <%--
                        An overview of registered
                        Controller => OverviewAllRegistered
                --%>

                <c:if test = "${role == 'Admin'}">


                    <c:if test = "${empty allRegistered}">

                        <p>There are no registered persons in the database</p>

                    </c:if>
                    <c:if test = "${not empty allRegistered}">

                        <table class='w3-table-all'>
                            <tr>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Group</th>
                                <th>Gsm number</th>
                            </tr>
                            <c:forEach var="registered" items="${allRegistered}">
                                <tr>
                                    <td>${registered.firstname}</td>
                                    <td>${registered.lastname}</td>
                                    <td>${registered.email}</td>
                                    <td>${registered.gsmnumber}</td>
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