package ui.controller;

import domain.model.Registered;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Login extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();

        Registered registered = getRegistered(request, result);
        if (registered != null) {
            checkPassword(registered, request, result);
        }

        if (result.size() > 0)  {
            request.setAttribute("result", result);
            return "index.jsp";
        }

        if (registered.getRole().equals("Guardian")) {
            return "Controller?command=OverviewUsersWithEmail";
        } else {
            return "Controller?command=OverviewUsers";
        }

    }

    private Registered getRegistered(HttpServletRequest request, List<String> result) {
        String email = request.getParameter("email");
        request.setAttribute("emailReturn", email);

        if (email.isEmpty()) {
            result.add("Email is empty");
            request.setAttribute("nameClass", "has-error");
            return null;
        }

        Registered registered = registeredService.getRegistered(email);
        if (registered.getEmail() == null) {
            result.add("Email not in system");
            request.setAttribute("nameClass", "has-error");
            return null;
        } else {
            request.setAttribute("nameClass", "has-succes");
            return registered;
        }
    }

    private void checkPassword(Registered registered, HttpServletRequest request, List<String> result) {
        String password = request.getParameter("password");

        if (password.isEmpty() || !registered.isCorrectPassword(password)) {
            result.add("Password is wrong");
            request.setAttribute("nameClass", "has-error");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("firstname", registered.getFirstname());
            session.setAttribute("email", registered.getEmail());
            session.setAttribute("role", registered.getRole());
            request.setAttribute("nameClass", "has-succes");
        }
    }
}


