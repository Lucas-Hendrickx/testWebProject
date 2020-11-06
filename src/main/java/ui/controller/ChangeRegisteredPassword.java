package ui.controller;

import domain.model.Registered;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ChangeRegisteredPassword extends RequestHandler{
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();
        HttpSession session = request.getSession();

        String passwordA = request.getParameter("changePasswordA");
        String passwordB = request.getParameter("changePasswordB");

        if (passwordA.isEmpty() && passwordB.isEmpty()) result.add("passwords are empty");
        else if (!passwordA.equals(passwordB)) result.add("Passwords do not match");

        if (result.size() > 0) {
            request.setAttribute("result", result);
            return "edit.jsp";
        } else {
            Registered registered = registeredService.getRegistered((String) session.getAttribute("email"));
            registered.setHashedPassword(passwordA);
            registeredService.changeRegisteredPassword(registered.getEmail(), registered.getPassword());
            return "edit.jsp";
        }
    }
}
