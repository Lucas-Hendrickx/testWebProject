package ui.controller;

import domain.model.Registered;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Function_ChangePassword extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> result = new ArrayList<String>();
        HttpSession session = request.getSession();

        String passwordA = request.getParameter("changePasswordA");
        String passwordB = request.getParameter("changePasswordB");

        if (passwordA.isEmpty() && passwordB.isEmpty()) result.add("passwords are empty");
        else if (!passwordA.equals(passwordB)) result.add("Passwords do not match");

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Index").forward(request, response);
        } else {
            Registered registered = service.getRegistered((String) session.getAttribute("email"));
            registered.setHashedPassword(passwordA);
            service.changeRegisteredPassword(registered.getEmail(), registered.getPassword());
            response.sendRedirect("Controller?command=Open_Index");
        }
    }
}
