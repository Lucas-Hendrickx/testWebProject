package ui.controller;

import domain.model.Registered;
import domain.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Open_Contact extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<String> result = new ArrayList<String>();
        String role = (String) session.getAttribute("role");

        // Not Admin
        if (role.isEmpty() || role.equals("Guardian")) {
            result.add("You need to be logged in to open corona tests");
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Index").forward(request, response);
        } else {

            // Logged In As Admin
            if (role.equals("Admin")) {
                String userid = request.getParameter("userid");
                String dateString = request.getParameter("date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
                LocalDateTime dateoftest = LocalDateTime.parse(dateString, formatter);
                int daysaftertest = 28;
                HashMap<User, Registered> allContactsOfUser = service.searchContactsOfUser(userid, dateoftest, daysaftertest);
                request.setAttribute("allContactsOfUser", allContactsOfUser);
            }
            request.getRequestDispatcher("contact.jsp").forward(request, response);
        }
    }
}
