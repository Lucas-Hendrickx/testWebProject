package ui.controller;

import domain.model.Event;
import domain.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Remove_User extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> result = new ArrayList<String>();

        User user = new User();
        setRegisteredEmail(user, request, result);
        setFirstname(user, request, result);
        setLastname(user, request, result);

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Overiew").forward(request, response);
        }

        User searchUser = service.getUser(user.getRegisteredEmail(), user.getFirstname(), user.getLastname());
        if (searchUser.getUserId() == null) {
            result.add("User not in system");
        } else {
            List<Event> allVisitsOfUser = service.getAllEventsOfUser(searchUser.getUserId());
            for (Event event : allVisitsOfUser) {
                service.removeUserFromEvent(searchUser.getUserId(), event.getEventId());
            }
        }

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Overiew").forward(request, response);
        } else {
            service.removeUser(searchUser.getUserId());
            response.sendRedirect("Controller?command=Open_Overview");
        }
    }

    // Setters

    private void setRegisteredEmail(User user, HttpServletRequest request, List<String> result) {
        String registeredEmail = request.getParameter("registeredEmail");
        if (registeredEmail.isEmpty()) {
            HttpSession session = request.getSession();
            registeredEmail = (String) session.getAttribute("email");
        } try {
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

