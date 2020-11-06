package ui.controller;

import domain.model.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OverviewEvents extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Event> allEvents = eventService.getAllEvents();
        request.setAttribute("allEvents", allEvents);
        return "contactregister.jsp";
    }
}
