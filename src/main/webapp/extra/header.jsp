<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<ul class="w3-sidebar w3-bar-block w3-collapse w3-card w3-dark-grey" style="width:200px;" id="mySidebar">
    <button class="w3-bar-item w3-button w3-large w3-hide-large" onclick="w3_close()">Close &times;</button>
    <li class="tablinks"><a href="index.jsp">Home</a></li>
    <li class="tablinks"><a href="Controller?command=Open_Form">Register</a></li>

    <c:if test="${registered.role=='ADMIN' || registered.role=='GUARDIAN'}">
        <li class="tablinks"><a href="Controller?command=Open_Overview">Overview</a></li>
        <li class="tablinks"><a href="Controller?command=Open_CoronaTest">CoronaTest</a></li>
    </c:if>

    <li class="tablinks"><a href="Controller?command=Open_Event">Events</a></li>
</ul>