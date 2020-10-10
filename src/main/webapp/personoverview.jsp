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

        <ul class="w3-sidebar w3-bar-block w3-collapse w3-card w3-dark-grey" style="width:200px;" id="mySidebar">
            <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
            <li class="tablinks" id="actual"><a href="index.jsp">Home</a></li>
            <li class="tablinks"><a href="Controller?command=Overview">Overview</a></li>
            <li class="tablinks"><a href="register.jsp">Register</a></li>
        </ul>

<!-------------------------------------------------------------------------------------------------------------------->

        <div class="w3-main" style="margin-left:200px">

<!--------- Titel ---------------------------------------------------------------------------------------------------->

            <div class="w3-dark-grey">
                <button class="w3-button w3-dark-grey w3-xlarge w3-hide-large" onclick="w3_open()">&#9776;</button>
                <div class="w3-container">
                    <h2>Overview</h2>
                </div>
            </div>

<!--------- Inhoud Pagina -------------------------------------------------------------------------------------------->

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

                <table class='w3-table-all'>
                    <tr>
                        <th>E-mail</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>
                    <c:forEach var="klant" items="${klanten}">
                        <tr>
                            <td>${klant.email}</td>
                            <td>${klant.firstName}</td>
                            <td>${klant.lastName}</td>
                        </tr>
                    </c:forEach>
                </table>

 <!-------------------------------------------------------------------------------------------------------------------->

                <footer>
                    &copy; Webontwikkeling 3, UC Leuven-Limburg
                </footer>

            </div>
        </div>
    </body>
</html>