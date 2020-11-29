package ui.controller;

import domain.model.Event;
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
            response.sendRedirect(removeRegisteredAndHisUsers(email));
        }
    }

    private String removeRegisteredAndHisUsers(String email) {
        List<User> users = service.getUsersWithEmail(email);
        for (User user : users) {
            List<Event> allVisitsOfUser = service.getAllEventsOfUser(user.getUserId());
            for (Event event : allVisitsOfUser) {
                service.removeUserFromEvent(user.getUserId(), event.getEventId());
            }
            service.removeUser(user.getUserId());
        }
        service.removeRegistered(email);
        return "Controller?command=Open_Overview";
    }
}
