package ui.controller;

import domain.model.Registered;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OverviewRegistered extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Registered> allRegistered = registeredService.getAllRegistered();
        request.setAttribute("allRegistered", allRegistered);
        return "overview.jsp";
    }
}
