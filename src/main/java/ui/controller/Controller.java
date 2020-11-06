package ui.controller;

import domain.service.RegisteredService;
import domain.service.UserService;
import domain.service.EventService;
import domain.service.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RegisteredService registeredService = new RegisteredService();
    private UserService       userService       = new UserService();
    private EventService      eventService      = new EventService();
    private VisitService      visitService      = new VisitService();
    private HandlerFactory    handlerFactory    = new HandlerFactory();

    public Controller() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String destination = "index.jsp";
        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, registeredService, userService,
                        eventService, visitService);
                destination = handler.handleRequest(request, response);
            } catch (Exception exc) {
                request.setAttribute("error", exc.getMessage());
                destination = "error.jsp";
            }
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}
