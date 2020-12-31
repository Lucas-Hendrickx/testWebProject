package ui.controller;

import domain.model.Registered;
import domain.model.Role;
import ui.authorization.Utility;
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
        Role[] roles = {Role.ADMIN, Role.GUARDIAN};
        Utility.checkRole(request, roles);

        List<String> result = new ArrayList<String>();
        HttpSession session = request.getSession();

        String passwordA = request.getParameter("changePasswordA");
        String passwordB = request.getParameter("changePasswordB");

        if (passwordA.isEmpty() && passwordB.isEmpty()) result.add("passwords are empty");
        else if (!passwordA.equals(passwordB)) result.add("Passwords do not match");

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            Registered registered = service.getRegistered((String) session.getAttribute("email"));
            registered.setHashedPassword(passwordA);
            service.changeRegisteredPassword(registered.getEmail(), registered.getPassword());
            request.setAttribute("success", "The password is successfully changed.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
