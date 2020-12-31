package ui.controller;

import domain.model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class Open_Event extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Event> allEvents = service.getAllEvents();
        request.setAttribute("allEvents", allEvents);

        List<String> eventidsWithCorona = service.getEventsWithCoronaPresent();
        request.setAttribute("eventidsWithCorona", eventidsWithCorona);

        request.getRequestDispatcher("event.jsp").forward(request, response);
    }

}
