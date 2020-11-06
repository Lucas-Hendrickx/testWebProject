package ui.controller;

import domain.service.RegisteredService;
import domain.service.UserService;
import domain.service.EventService;
import domain.service.VisitService;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, RegisteredService registeredService, UserService userService,
                                     EventService eventService, VisitService visitService) {
        RequestHandler handler = null;
        try {
            Class handlerClass   = Class.forName("ui.controller." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler              = (RequestHandler) handlerObject;
            handler.setModel(registeredService, userService, eventService, visitService);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!");
        }
        return handler;
    }
}
