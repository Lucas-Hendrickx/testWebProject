package ui.controller;

import domain.model.Role;
import domain.model.User;
import ui.authorization.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Function_RemoveUserOfEvent extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.ADMIN, Role.GUARDIAN};
        Utility.checkRole(request, roles);

        User user = service.getUser(request.getParameter("registeredEmail"), request.getParameter("firstname"), request.getParameter("lastname"));
        String eventId = request.getParameter("eventId");
        service.removeUserFromEvent(user.getUserId(), eventId);
        request.setAttribute("success", "You successfully removed the user form the event.");
        response.sendRedirect("Controller?command=Open_Details&eventId=" + eventId);
    }

}