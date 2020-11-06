package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OverviewUsersWithEmail extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        List<User> allUsersWithEmail = userService.getUsersWithEmail((String) session.getAttribute("email"));
        request.setAttribute("allUsersWithEmail", allUsersWithEmail);
        return "index.jsp";
    }
}
