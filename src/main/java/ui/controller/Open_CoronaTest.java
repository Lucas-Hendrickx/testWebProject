package ui.controller;

import domain.model.Registered;
import domain.model.Role;
import domain.model.User;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Open_CoronaTest extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.GUARDIAN};
        Utility.checkRole(request, roles);

        HttpSession session = request.getSession();
        List<String> result = new ArrayList<String>();
        Registered registered = (Registered) session.getAttribute("registered");

        // Logged In As Guardian
        if (registered.getRole().equals(Role.GUARDIAN)) {
            String email = (String) session.getAttribute("email");
            List<User> allUsersWithEmail = service.getUsersWithEmail(email);
            request.setAttribute("allUsersWithEmail", allUsersWithEmail);
            HashMap<String, User> allCoronaTestsOfRegistered = service.getAllCoronaTestsOfRegistered(email);
            request.setAttribute("allCoronaTestsOfRegistered", allCoronaTestsOfRegistered);
        }

        // Logged In As Admin
        if (registered.getRole().equals(Role.ADMIN)) {
            HashMap<String, User> allCoronaTests = service.getAllCoronaTests();
            request.setAttribute("allCoronaTests", allCoronaTests);
        }

        request.getRequestDispatcher("coronatest.jsp").forward(request, response);

    }
}
