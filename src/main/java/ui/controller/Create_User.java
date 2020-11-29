package ui.controller;

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

public class Create_User extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.GUARDIAN};
        Utility.checkRole(request, roles);

        List<String> result = new ArrayList<String>();
        User user = new User();

        setRegisteredEmail(user, request, result);
        setFirstname(user, request, result);
        setLastname(user, request, result);
        setUsergroup(user, request, result);

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("form.jsp").forward(request, response);
        } else {
            User searchUser = service.getUser(user.getRegisteredEmail(), user.getFirstname(), user.getLastname());
            if (searchUser.getUserId() != null) {
                result.add("User already exists");
                request.setAttribute("result", result);
                request.getRequestDispatcher("form.jsp").forward(request, response);
            } else {
                service.addUser(user);
                response.sendRedirect("Controller?command=Open_Overview");
            }
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

    private void setUsergroup(User user, HttpServletRequest request, List<String> result) {
        String usergroup = request.getParameter("group");
        request.setAttribute("usergroupReturn", usergroup);
        try {
            user.setUsergroup(usergroup);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }
}
