package ui.controller;

import domain.model.Event;
import domain.model.User;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Function_AddUserToEvent extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> result = new ArrayList<>();
        String fullname = request.getParameter("user");
        String eventId = request.getParameter("eventId");
        List<User> usersInEvent = service.getAllUsersOfEvent(eventId);
        Event event = service.getEvent(eventId);

        if (usersInEvent.size() == event.getAmountOfPeopleAllowed()) {

            result.add("The event is full, you cannot add more users");
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Details&eventId=" + eventId).forward(request, response);

        } else if (fullname == null) {

            result.add("You first need to select a user.");
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Details&eventId=" + eventId).forward(request, response);

        } else {

            String[] namesplit = request.getParameter("user").split(" ");
            User user = service.getUser((String) request.getSession().getAttribute("email"), namesplit[0], namesplit[1]);

            for (User searchUser : usersInEvent) {
                if (searchUser.getUserId().equals(user.getUserId())) {
                    result.add("User is already registered in the event");
                    request.setAttribute("result", result);
                    request.getRequestDispatcher("Controller?command=Open_Details&eventId=" + eventId).forward(request, response);
                }
            }
            service.addUserToEvent(user.getUserId(), eventId);
            response.sendRedirect("Controller?command=Open_Details&eventId=" + eventId);
        }
    }
}
