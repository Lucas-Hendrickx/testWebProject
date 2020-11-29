<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<ul class="w3-sidebar w3-bar-block w3-collapse w3-card w3-dark-grey" style="width:200px;" id="mySidebar">
    <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>

<!------------- Not Logged In ---------------------------------------------------------------------------------------->

    <c:if test = "${empty role}">

        <li class="tablinks"><a href="Controller?command=Open_Index">Home</a></li>
        <li class="tablinks"><a href="Controller?command=Open_Form">Register</a></li>

    </c:if>

<!------------- Logged In As Guardian -------------------------------------------------------------------------------->

    <c:if test = "${role == 'Guardian'}">

        <li class="tablinks"><a href="Controller?command=Open_Index">Home</a></li>
        <li class="tablinks"><a href="Controller?command=Open_Form">Register</a></li>
        <li class="tablinks"><a href="Controller?command=Open_Overview">Overview</a></li>
        <li class="tablinks"><a href="Controller?command=Open_CoronaTest">CoronaTest</a></li>


    </c:if>

<!------------- Logged In As Admin ----------------------------------------------------------------------------------->

    <c:if test = "${role == 'Admin'}">

        <li class="tablinks"><a href="Controller?command=Open_Index">Home</a></li>
        <li class="tablinks"><a href="Controller?command=Open_Form">Register</a></li>
        <li class="tablinks"><a href="Controller?command=Open_Overview">Overview</a></li>
        <li class="tablinks"><a href="Controller?command=Open_CoronaTest">CoronaTest</a></li>


    </c:if>

<!-------------------------------------------------------------------------------------------------------------------->

    <li class="tablinks"><a href="Controller?command=Open_Event">Events</a></li>
</ul>