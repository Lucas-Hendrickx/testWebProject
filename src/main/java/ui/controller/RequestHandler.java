package ui.controller;

import domain.service.RegisteredService;
import domain.service.UserService;
import domain.service.EventService;
import domain.service.VisitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected RegisteredService registeredService;
    protected UserService userService;
    protected EventService eventService;
    protected VisitService visitService;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public void setModel (RegisteredService registeredService, UserService userService,
                          EventService eventService, VisitService visitService) {
        this.registeredService = registeredService;
        this.userService = userService;
        this.eventService = eventService;
        this.visitService = visitService;
    }
}
