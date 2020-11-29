package ui.controller;

import domain.model.Event;
import ui.controller.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Remove_Event extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> result = new ArrayList<>();
        String eventId = request.getParameter("eventId");

        Event event = service.getEvent(eventId);
        if (event.getEventId() == null) {
            result.add("Event not in system");
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_Overview").forward(request, response);
        } else {
            service.removeEvent(eventId);
            response.sendRedirect("Controller?command=Open_Overview");
        }
    }
}
