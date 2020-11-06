package ui.controller;

import domain.model.Event;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddUserToEvent extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<>();
        String fullname = request.getParameter("user");
        String eventId = request.getParameter("eventId");
        List<User> usersInEvent = visitService.getAllUsersOfEvent(eventId);
        Event event = eventService.getEvent(eventId);

        if (usersInEvent.size() == event.getAmountOfPeopleAllowed()) {
            result.add("The event is full, you cannot add more users");
            request.setAttribute("result", result);
            return "Controller?command=OpenContactList&eventId=" + event.getEventId();
        }

        if (fullname == null) {
            result.add("You first need to select a user.");
            request.setAttribute("result", result);
            return "Controller?command=OpenContactList&eventId=1";
        }

        String[] namesplit = request.getParameter("user").split(" ");
        User user = userService.getUser((String) request.getSession().getAttribute("email"), namesplit[0], namesplit[1]);

        for (User searchUser : usersInEvent) {
            if (searchUser.getUserId().equals(user.getUserId())) {
                result.add("User is already registered in the event");
                request.setAttribute("result", result);
                return "Controller?command=OpenContactList&eventId=" + event.getEventId();
            }
        }
        visitService.addUserToEvent(user.getUserId(), eventId);
        return "Controller?command=OpenContactList&eventId=" + event.getEventId();
    }
}
