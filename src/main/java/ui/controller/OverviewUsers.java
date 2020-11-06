package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OverviewUsers extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<User> allUsers = userService.getAllUsers();
        request.setAttribute("allUsers", allUsers);
        return "index.jsp";
    }
}
