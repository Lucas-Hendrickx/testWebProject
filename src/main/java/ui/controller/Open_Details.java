package ui.controller;

import domain.model.Event;
import domain.model.Registered;
import domain.model.Role;
import domain.model.User;
import ui.authorization.Utility;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Open_Details extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.GUARDIAN};
        Utility.checkRole(request, roles);

        HttpSession session = request.getSession();
        List<String> result = new ArrayList<String>();
        Registered registered = (Registered) session.getAttribute("registered");


        // Logged In As Guardian
        if (registered.getRole().equals(Role.GUARDIAN)) {
            List<User> allUsersWithEmail = service.getUsersWithEmail((String) session.getAttribute("email"));
            request.setAttribute("allUsersWithEmail", allUsersWithEmail);
        }

        // Logged In
        String eventId = request.getParameter("eventId");
        List<User> allUsersOfEvent = service.getAllUsersOfEvent(eventId);
        Event event = service.getEvent(eventId);
        request.setAttribute("allUsersOfEvent", allUsersOfEvent);
        request.setAttribute("eventId", eventId);
        request.setAttribute("eventname", event.getName());
        request.setAttribute("amountOfUsers", allUsersOfEvent.size());
        request.setAttribute("amountOfPeopleAllowed", event.getAmountOfPeopleAllowed());

        request.getRequestDispatcher("details.jsp").forward(request, response);

    }

}
