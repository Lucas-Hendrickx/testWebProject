package ui.controller;

import domain.model.Event;
import domain.model.Role;
import ui.authorization.Utility;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Create_Event extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        List<String> result = new ArrayList<>();
        Event event =  new Event();

        setName(event, request, result);
        setDate(event, request, result);
        setDuration(event, request, result);
        setAmountOfPeopleAllowed(event, request, result);

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("form.jsp").forward(request, response);
        } else {
            service.addEvent(event);
            request.setAttribute("success", "The event is successfully added.");
            request.getRequestDispatcher("Controller?command=Open_Event").forward(request, response);
        }
    }


    /***
     * Setters
     */
    private void setName(Event event, HttpServletRequest request, List<String> result) {
        String name = request.getParameter("name");
        request.setAttribute("nameReturn", name);
        try {
            event.setName(name);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setDate(Event event, HttpServletRequest request, List<String> result) {
        String date = request.getParameter("date");
        request.setAttribute("dateReturn", date);
        String time = request.getParameter("time");
        request.setAttribute("timeReturn", time);
        try {
            event.setDateFromString(date, time);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setDuration(Event event, HttpServletRequest request, List<String> result) {
        String duration = request.getParameter("duration");
        request.setAttribute("durationReturn", duration);
        try {
            event.setDuration(Integer.parseInt(duration));
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            if(exc.getMessage().equals("For input string: \"\"")) {
                result.add("Duration is empty");
            } else {
                result.add(exc.getMessage());
            }
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setAmountOfPeopleAllowed(Event event, HttpServletRequest request, List<String> result) {
        String amount = request.getParameter("amount");
        request.setAttribute("amountReturn", amount);
        try {
            event.setAmountOfPeopleAllowed(Integer.parseInt(amount));
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            if(exc.getMessage().equals("For input string: \"\"")) {
                result.add("Amount of People Allowed is empty");
            } else {
                result.add(exc.getMessage());
            }
            request.setAttribute("nameClass", "has-error");
        }
    }

}
