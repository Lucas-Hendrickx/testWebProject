package ui.controller;

import domain.model.Registered;
import domain.model.User;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Open_Overview extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<String> result = new ArrayList<String>();

        try {
            String role = (String) session.getAttribute("role");

            // Logged In As Guardian
            if (role.equals("Guardian")) {
                List<User> allUsersWithEmail = service.getUsersWithEmail((String) session.getAttribute("email"));
                request.setAttribute("allUsersWithEmail", allUsersWithEmail);
            }

            // Logged In As Admin
            if (role.equals("Admin")) {
                List<User> allUsers = service.getAllUsers();
                List<Registered> allRegistered = service.getAllRegistered();
                request.setAttribute("allUsers", allUsers);
                request.setAttribute("allRegistered", allRegistered);
            }
        } catch (Exception exc){
            result.add("You need to be logged in to go to the Overview");
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Index").forward(request, response);
        }
        request.getRequestDispatcher("overview.jsp").forward(request, response);
    }
}
