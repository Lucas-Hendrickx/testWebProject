package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Function_RemoveUserOfEvent extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = service.getUser(request.getParameter("registeredEmail"), request.getParameter("firstname"), request.getParameter("lastname"));
        String eventId = request.getParameter("eventId");
        service.removeUserFromEvent(user.getUserId(), eventId);
        response.sendRedirect("Controller?command=Open_Details&eventId=" + eventId);
    }
}
