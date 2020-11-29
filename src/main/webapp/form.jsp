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

        <jsp:include page="extra/header.jsp"/>

        <div class="w3-main" style="margin-left:200px">

<!--------- Title ---------------------------------------------------------------------------------------------------->

            <jsp:include page="extra/title.jsp">
                <jsp:param name="title" value="Register" />
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

<!------------- Not Logged In ---------------------------------------------------------------------------------------->

                <c:if test = "${empty role}">

                    <form method="POST" action="Controller?command=Create_Registered" novalidate="novalidate">

                        <p><label for="registeredEmail">Email</label>
                            <input type="email" id="registeredEmail" name="email" value="<c:out value='${emailReturn}'/>" required ></p>

                        <p><label for="registeredFirstname">Firstname</label>
                            <input type="text" id="registeredFirstname" name="firstname" value="<c:out value='${firstnameReturn}'/>" required > </p>

                        <p><label for="registeredLastname">Lastname</label>
                            <input type="text" id="registeredLastname" name="lastname" value="<c:out value='${lastnameReturn}'/>" required > </p>

                        <p><label for="registeredGsmnumber">Gsmnumber</label>
                            <input type="email" id="registeredGsmnumber" name="gsmnumber" value="<c:out value='${gsmnumberReturn}'/>" required ></p>

                        <p><label for="registeredPassword">Password</label>
                            <input type="password" id="registeredPassword"  name="password" required > </p>

                        <p><input type="submit" id="registeredSignup" value="Sign Up"></p>

                    </form>


                </c:if>

<!------------- Logged In As Guardian -------------------------------------------------------------------------------->

                <c:if test = "${role == 'Guardian'}">

                    <form method="POST" action="Controller?command=Create_User" novalidate="novalidate">

                        <p><label for="userFirstname">Firstname</label>
                            <input type="text" id="userFirstname" name="firstname" value="<c:out value='${firstnameReturn}'/>" required > </p>

                        <p><label for="userLastname">Lastname</label>
                            <input type="text" id="userLastname" name="lastname" value="<c:out value='${lastnameReturn}'/>" required > </p>

                        <p><label for="userGroup">Group</label>
                            <select class="w3-select" id='userGroup' name="group" value="<c:out value='${usergroupReturn}'/>" required >
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

                <c:if test = "${role == 'Admin'}">

                    <form method="POST" action="Controller?command=Create_Event" novalidate="novalidate">

                        <p><label for="eventName">Name</label>
                            <input type="text" id="eventName" name="name" value="<c:out value='${nameReturn}'/>" required> </p>

                        <p><label for="eventDate">Date</label>
                            <input class="w3-input w3-border w3-round" type="date" id="eventDate" name="date" value="<c:out value='${dateReturn}'/>" required> </p>

                        <p><label for="eventTime">Time</label>
                            <input class="w3-input w3-border w3-round" type="time" id="eventTime" name="time" value="<c:out value='${timeReturn}'/>" required> </p>

                        <p><label for="eventDuration">Duration (In Hours)</label>
                            <input class="w3-input w3-border w3-round" type="number" id="eventDuration" name="duration" value="<c:out value='${durationReturn}'/>" required> </p>

                        <p><label for="eventAmount">Amount of people allowed</label>
                            <input class="w3-input w3-border w3-round" type="number" id="eventAmount" name="amount" value="<c:out value='${amountReturn}'/>" required> </p>

                        <p><input type="submit" id="eventCreate" value="Create"></p>

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