<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Sign Up</title>
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
                    A title from "title.jsp" with value "Register"
             --%>

            <jsp:include page="title.jsp">
                <jsp:param name="title" value="Register" />
            </jsp:include>

            <div class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">

<!------------- Errors ----------------------------------------------------------------------------------------------->

                <%--
                        A list with all errors with destination "form.jsp"
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
                        A form to create a new registered guardian
                        Controller => AddRegistered
                --%>

                <c:if test = "${empty role}">

                    <form method="POST" action="Controller?command=AddRegistered" novalidate="novalidate">

                        <p><label for="registeredEmail">Email</label>
                            <input type="email" id="registeredEmail" name="email" value="${emailReturn}" required ></p>

                        <p><label for="registeredFirstname">Firstname</label>
                            <input type="text" id="registeredFirstname" name="firstname" value="${firstnameReturn}" required > </p>

                        <p><label for="registeredLastname">Lastname</label>
                            <input type="text" id="registeredLastname" name="lastname" value="${lastnameReturn}" required > </p>

                        <p><label for="registeredGsmnumber">Gsmnumber</label>
                            <input type="email" id="registeredGsmnumber" name="gsmnumber" value="${gsmnumberReturn}" required ></p>

                        <p><label for="registeredPassword">Password</label>
                            <input type="password" id="registeredPassword"  name="password" required > </p>

                        <p><input type="submit" id="registeredSignup" value="Sign Up"></p>

                    </form>


                </c:if>

<!------------- Logged In As Guardian -------------------------------------------------------------------------------->

                <%--
                        A form to create a new user
                        Controller => AddUser
                --%>

                <c:if test = "${role == 'Guardian'}">

                    <form method="POST" action="Controller?command=AddUser" novalidate="novalidate">

                        <p><label for="userFirstname">Firstname</label>
                            <input type="text" id="userFirstname" name="firstname" value="${firstnameReturn}" required > </p>

                        <p><label for="userLastname">Lastname</label>
                            <input type="text" id="userLastname" name="lastname" value="${lastnameReturn}" required > </p>

                        <p><label for="userGroup">Group</label>
                            <select class="w3-select" id='userGroup' name="group" value="${usergroupReturn}" required >
                                <option value="" disabled selected>Select group</option>
                                <option value="Biekes">Biekes</option>
                                <option value="Speelclub Jongens">Speelclub Jongens</option>
                                <option value="Speelclub Meisjes">Speelclub Meisjes</option>
                                <option value="Rakkers">Rakkers</option>
                                <option value="Kwiks">Kwiks</option>
                                <option value="Toppers">Toppers</option>
                                <option value="Tippers">Tippers</option>
                                <option value="Kerels">Kerels</option>
                                <option value="Tiptiens">Tiptiens</option>
                                <option value="Aspiranten Jongens">Aspiranten Jongens</option>
                                <option value="Aspiranten Meisjes">Aspiranten Meisjes</option>
                            </select></p>

                        <p><input type="submit" id="userSignup" value="Sign Up"></p>

                    </form>

                </c:if>

<!------------- Logged In As Admin ----------------------------------------------------------------------------------->

                <%--
                        An overview of users
                        Controller => AddEvent
                --%>

                <c:if test = "${role == 'Admin'}">

                    <h2>Admin, not yet added (its for making events)</h2>

                </c:if>

<!------------- Footer ----------------------------------------------------------------------------------------------->

                <footer>
                    &copy; Webontwikkeling 3, UC Leuven-Limburg
                </footer>

            </div>
        </div>
    </body>
</html>


<%--
        <form method="POST" action="Controller?command=Add" novalidate="novalidate">

            <p><label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" value="${fnPre}" required > </p>

            <p><label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" value="${lnPre}" required > </p>

            <p><label for="group">Group</label>
                <select class="w3-select" id='group' name="option" value="${grPre}" required >
                    <option value="" disabled selected>Select group</option>
                    <option value="Biekes">Biekes</option>
                    <option value="Speelclub Jongens">Speelclub Jongens</option>
                    <option value="Speelclub Meisjes">Speelclub Meisjes</option>
                    <option value="Rakkers">Rakkers</option>
                    <option value="Kwiks">Kwiks</option>
                    <option value="Toppers">Toppers</option>
                    <option value="Tippers">Tippers</option>
                    <option value="Kerels">Kerels</option>
                    <option value="Tiptiens">Tiptiens</option>
                    <option value="Aspiranten Jongens">Aspiranten Jongens</option>
                    <option value="Aspiranten Meisjes">Aspiranten Meisjes</option>
                </select></p>

            <p><label for="email">Email</label>
                <input type="email" id="email" name="email" value="${emPre}" required ></p>

            <p><label for="password">Password</label>
                <input type="password" id="password"  name="password" value="${pwPre}" required > </p>

            <p><input type="submit" id="signUp" value="Sign Up"></p>

        </form>
--%>