package ui.controller;

import domain.model.Registered;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class RemoveRegistered extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();
        String email = request.getParameter("email");
        request.setAttribute("emailReturn", email);
        HttpSession session = request.getSession();


        if (email.isEmpty()) {
            result.add("Email is empty");
        } else if (registeredService.getRegistered(email).getEmail() == null){
            result.add("Email not in system");
        } else if (session.getAttribute("email").equals(email)) {
            result.add("Cannot remove logged in person");
        }


        if (result.size() > 0) {
            request.setAttribute("result", result);
            return "edit.jsp";
        } else {
            return removeRegisteredAndHisUsers(email);
        }
    }

    private String removeRegisteredAndHisUsers(String email) {
        List<User> users = userService.getUsersWithEmail(email);
        for (User user : users) {
            userService.removeUser(user.getUserId());
        }
        registeredService.removeRegistered(email);
        return "Controller?command=OverviewAllUsers";
    }
}
