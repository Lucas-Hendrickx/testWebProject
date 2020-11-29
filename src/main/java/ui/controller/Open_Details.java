package ui.controller;

import domain.model.Event;
import domain.model.User;
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
        HttpSession session = request.getSession();
        List<String> result = new ArrayList<String>();
        String role = (String) session.getAttribute("role");

        // Not Logged In
        if (role.isEmpty()) {
            result.add("You need to be logged in to open the Details");
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Index").forward(request, response);
        } else {

            // Logged In As Guardian
            if (role.equals("Guardian")) {
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
}
