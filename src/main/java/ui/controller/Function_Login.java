package ui.controller;

import domain.model.Registered;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Function_Login extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> result = new ArrayList<String>();

        Registered registered = getRegistered(request, result);
        if (registered != null) {
            checkPassword(registered, request, result);
        }

        if (result.size() > 0)  {
            request.setAttribute("result", result);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            response.sendRedirect("Controller?command=Open_Index");
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

        Registered registered = service.getRegistered(email);
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
            request.getSession().setAttribute("firstname", registered.getFirstname());
            request.getSession().setAttribute("email", registered.getEmail());
            request.getSession().setAttribute("role", registered.getRole());
            request.setAttribute("nameClass", "has-succes");
        }
    }
}
