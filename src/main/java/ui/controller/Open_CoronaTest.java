package ui.controller;

import domain.model.User;

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
        HttpSession session = request.getSession();
        List<String> result = new ArrayList<String>();
        String role = (String) session.getAttribute("role");

        // Not Logged In
        if (role.isEmpty()) {
            result.add("You need to be logged in to open corona tests");
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Index").forward(request, response);
        } else {

            // Logged In As Guardian
            if (role.equals("Guardian")) {
                String email = (String) session.getAttribute("email");
                List<User> allUsersWithEmail = service.getUsersWithEmail(email);
                request.setAttribute("allUsersWithEmail", allUsersWithEmail);
                HashMap<String, User> allCoronaTestsOfRegistered = service.getAllCoronaTestsOfRegistered(email);
                request.setAttribute("allCoronaTestsOfRegistered", allCoronaTestsOfRegistered);
            }

            // Logged In As Admin
            if (role.equals("Admin")) {
                HashMap<String, User> allCoronaTests= service.getAllCoronaTests();
                request.setAttribute("allCoronaTests", allCoronaTests);
            }
            request.getRequestDispatcher("coronatest.jsp").forward(request, response);
        }
    }
}
