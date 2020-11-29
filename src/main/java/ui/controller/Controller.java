package ui.controller;

import domain.service.Service;
import ui.authorization.NotAuthorizedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Service service = new Service();
    private HandlerFactory handlerFactory = new HandlerFactory();

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
        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, service);
                handler.handleRequest(request, response);
            }
            catch (NotAuthorizedException e) {
                List<String> result = new ArrayList<String>();
                result.add("You have insufficient rights to have a look at the page");
                request.setAttribute("result", result);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            catch (Exception exc) {
                List<String> result = new ArrayList<String>();
                result.add(exc.getMessage());
                request.setAttribute("result", result);
                request.getRequestDispatcher("extra/error.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
