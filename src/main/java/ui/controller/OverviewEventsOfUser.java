package ui.controller;

import domain.model.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OverviewEventsOfUser extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        List<Event> eventsOfUser = visitService.getAllEventsOfUser(userId);
        request.setAttribute("eventsOfUser", eventsOfUser);
        return "contactregister.jsp";
    }
}
