package ui.controller;

import domain.model.Event;
import domain.model.Role;
import domain.model.User;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Remove_Registered extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        List<String> result = new ArrayList<String>();
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        if (email.isEmpty()) {
            result.add("Email is empty");
        } else if (service.getRegistered(email).getEmail() == null){
            result.add("Email not in system");
        } else if (session.getAttribute("email").equals(email)) {
            result.add("Cannot remove logged in person");
        }

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Overview").forward(request, response);
        } else {
            removeRegisteredAndHisUsers(email, request, response);
        }
    }

    private void removeRegisteredAndHisUsers(String email, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = service.getUsersWithEmail(email);
        for (User user : users) {
            removeUserFromTheEvent(user);
            service.removeCoronaTestsOfUser(user.getUserId());
            service.removeUser(user.getUserId());
        }
        service.removeRegistered(email);

        request.setAttribute("success", "You successfully removed the guardian.");
        request.getRequestDispatcher("Controller?command=Open_Overview").forward(request, response);
    }


    /***
     * Functions
     */
    private void removeUserFromTheEvent(User user) {
        List<Event> allVisitsOfUser = service.getAllEventsOfUser(user.getUserId());
        for (Event event : allVisitsOfUser) {
            service.removeUserFromEvent(user.getUserId(), event.getEventId());
        }
    }

}
