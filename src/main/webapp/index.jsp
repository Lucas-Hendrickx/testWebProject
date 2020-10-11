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

<ul class="w3-sidebar w3-bar-block w3-collapse w3-card w3-dark-grey" style="width:200px;" id="mySidebar">
	<button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
	<li class="tablinks"><a href="index.jsp">Home</a></li>
	<li class="tablinks"><a href="Controller?command=Overview">Overview</a></li>
	<li class="tablinks"><a href="register.jsp">Register</a></li>
	<li class="tablinks"><a href="remove.jsp">Remove</a></li>
</ul>

<!-------------------------------------------------------------------------------------------------------------------->

<div class="w3-main" style="margin-left:200px">

<!--------- Titel ---------------------------------------------------------------------------------------------------->

	<div class="w3-dark-grey">
		<button class="w3-button w3-dark-grey w3-xlarge w3-hide-large" onclick="w3_open()">&#9776;</button>
		<div class="w3-container">
			<h2>Home</h2>
		</div>
	</div>

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

<!------------- Form ------------------------------------------------------------------------------------------------->

		<c:if test = "${empty firstName}">

			<form method="POST" action="Controller?command=Login" novalidate="novalidate">

				<p><label for="userid">User id</label>
					<input type="text" id="userid" name="userid" value="${loginId}" required > </p>

				<p><label for="password">Password</label>
					<input type="password" id="password"  name="password" required > </p>

				<p><input type="submit" id="logIn" value="Log in"></p>
			</form>

		</c:if>

<!------------- Logged In -------------------------------------------------------------------------------------------->

		<c:if test = "${not empty firstName}">

			<h2>Welcome ${firstName}!</h2>

			<form method="POST" action="Controller?command=Logout" novalidate="novalidate">
				<p><input type="submit" id="logOut" value="Log out"></p>
			</form>

			<h2>If you would like to change password:</h2>

			<form method="POST" action="Controller?command=Change" novalidate="novalidate">
				<p><label for="userid">Password</label>
					<input type="password" id="changePassword" name="password" required > </p>

				<p><label for="password">Repeat Password</label>
					<input type="password" id="passwordRepeat"  name="repeatPassword" required > </p>

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