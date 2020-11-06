package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class RemoveUser extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();

        User user = new User();
        setRegisteredEmail(user, request, result);
        setFirstname(user, request, result);
        setLastname(user, request, result);

        if (result.size() > 0) {
            request.setAttribute("result", result);
            return "edit.jsp";
        }

        User searchUser = userService.getUser(user.getRegisteredEmail(), user.getFirstname(), user.getLastname());
        if (searchUser.getUserId() == null) {
            result.add("User not in system");
        }

        if (result.size() > 0) {
            request.setAttribute("result", result);
            return "edit.jsp";
        } else {
            userService.removeUser(searchUser.getUserId());
            return "Controller?command=OverviewUsersWithEmail";
        }
    }

    // Setters

    private void setRegisteredEmail(User user, HttpServletRequest request, List<String> result) {
        HttpSession session = request.getSession();
        String registeredEmail = (String) session.getAttribute("email");
        try {
            user.setRegisteredEmail(registeredEmail);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setFirstname(User user, HttpServletRequest request, List<String> result) {
        String firstname = request.getParameter("firstname");
        request.setAttribute("firstnameReturn", firstname);
        try {
            user.setFirstname(firstname);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setLastname(User user, HttpServletRequest request, List<String> result) {
        String lastname = request.getParameter("lastname");
        request.setAttribute("lastnameReturn", lastname);
        try {
            user.setLastname(lastname);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }
}
