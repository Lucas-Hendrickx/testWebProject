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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Function_Filter extends RequestHandler{

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.GUARDIAN};
        Utility.checkRole(request, roles);
        List<String> result = new ArrayList<>();
        HashMap<String, User> data = new HashMap<>();

        HttpSession session = request.getSession();
        Registered registered = (Registered) session.getAttribute("registered");

        String fromDate = request.getParameter("fromDate");
        Timestamp from = Timestamp.valueOf(fromDate +" 00:00:00");
        request.setAttribute("fromDateReturn", fromDate);

        String untilDate = request.getParameter("untilDate");
        Timestamp until = Timestamp.valueOf(untilDate +" 00:00:00");
        request.setAttribute("untilDateReturn", untilDate);

        if (registered.getRole().getStringValue().equals("Guardian")) {
            String email = ((Registered) session.getAttribute("registered")).getEmail();
            data = service.getAllCoronaTestsOfRegisteredBetweenDates(email, from, until);
            List<User> allUsersWithEmail = service.getUsersWithEmail(email);

            request.setAttribute("allUsersWithEmail", allUsersWithEmail);
            request.setAttribute("allCoronaTestsOfRegistered", data);


        } else {
            String email = request.getParameter("registered");
            List<Registered> allRegistered = service.getAllRegistered();
            if (email == null) {
                data = service.getAllCoronaTestsBetweenDates(from, until);
            } else {
                data = service.getAllCoronaTestsOfRegisteredBetweenDates(email, from, until);
            }
            request.setAttribute("allRegistered", allRegistered);
            request.setAttribute("allCoronaTests", data);
        }

        // From boolean Until boolean Registered boolean
        // HashMap<String, User> data = service.getCoronaTests()

        request.setAttribute("success", "The filter is successfully added.");
        request.getRequestDispatcher("coronatest.jsp").forward(request, response);
    }

}
