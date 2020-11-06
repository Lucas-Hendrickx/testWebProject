package ui.controller;

import domain.model.Event;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OpenContactList extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        // Get The parameters for the overview:
        String eventId = request.getParameter("eventId");
        List<User> usersInEvent  = visitService.getAllUsersOfEvent(eventId);
        Event event = eventService.getEvent(eventId);
        request.setAttribute("event", event);
        request.setAttribute("usersInEvent", usersInEvent);
        request.setAttribute("amountOfUsers", usersInEvent.size());

        // Get The parameters for the form:
        HttpSession session = request.getSession();
        List<User> allUsersWithEmail = userService.getUsersWithEmail((String) session.getAttribute("email"));
        request.setAttribute("allUsersWithEmail", allUsersWithEmail);

        // Bring it to contactRegister.jsp
        return "contactregister.jsp";
    }
}
