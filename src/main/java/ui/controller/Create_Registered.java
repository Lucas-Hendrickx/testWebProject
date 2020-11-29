package ui.controller;

import domain.model.Registered;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Create_Registered extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> result = new ArrayList<String>();
        Registered registered = new Registered();

        setEmail(registered, request, result);
        setGsmnumber(registered, request, result);
        setFirstname(registered, request, result);
        setLastName(registered, request, result);
        setPassword(registered, request, result);

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("form.jsp").forward(request, response);
        } else {
            Registered searchRegistered = service.getRegistered(registered.getEmail());
            if (searchRegistered.getEmail() != null) {
                result.add("User already exists");
                request.setAttribute("result", result);
                request.getRequestDispatcher("form.jsp").forward(request, response);
            } else {
                service.addRegistered(registered);
                response.sendRedirect("index.jsp");
            }
        }
    }

    // Setters

    private void setEmail(Registered registered, HttpServletRequest request, List<String> result) {
        String email = request.getParameter("email");
        request.setAttribute("emailReturn", email);
        try {
            registered.setEmail(email);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setGsmnumber(Registered registered, HttpServletRequest request, List<String> result) {
        String gsmnumber = request.getParameter("gsmnumber");
        request.setAttribute("gsmnumberReturn", gsmnumber);
        try {
            registered.setGsmnumber(gsmnumber);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setFirstname(Registered registered, HttpServletRequest request, List<String> result) {
        String firstname = request.getParameter("firstname");
        request.setAttribute("firstnameReturn", firstname);
        try {
            registered.setFirstname(firstname);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setLastName(Registered registered, HttpServletRequest request, List<String> result) {
        String lastname = request.getParameter("lastname");
        request.setAttribute("lastnameReturn", lastname);
        try {
            registered.setLastname(lastname);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setPassword(Registered registered, HttpServletRequest request, List<String> result) {
        String password = request.getParameter("password");
        try {
            registered.setHashedPassword(password);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }
}
