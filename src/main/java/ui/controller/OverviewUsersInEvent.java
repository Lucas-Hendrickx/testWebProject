package ui.controller;

import domain.model.Event;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OverviewUsersInEvent extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String eventId = request.getParameter("eventId");
        List<User> usersInEvent  = visitService.getAllUsersOfEvent(eventId);
        Event event = eventService.getEvent(eventId);
        request.setAttribute("event", event);
        request.setAttribute("usersInEvent", usersInEvent);
        request.setAttribute("amountOfUsers", usersInEvent.size());
        return "Controller?command=OverviewUsersWithEmail";
    }
}
