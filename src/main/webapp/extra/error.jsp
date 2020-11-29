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

<jsp:include page="header.jsp"/>

<!-------------------------------------------------------------------------------------------------------------------->

<div class="w3-main" style="margin-left:200px">

    <!--------- Titel ---------------------------------------------------------------------------------------------------->

    <jsp:include page="title.jsp">
        <jsp:param name="title" value="Error Page" />
    </jsp:include>

    <!--------- Inhoud Pagina -------------------------------------------------------------------------------------------->

    <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

<!------------- Errors ----------------------------------------------------------------------------------------------->

        <c:if test = "${not empty result}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items = "${result}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

<!------------- Footer ----------------------------------------------------------------------------------------------->

        <footer>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </footer>

    </div>
</div>
</body>
</html>