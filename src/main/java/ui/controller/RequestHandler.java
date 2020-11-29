package ui.controller;

import domain.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {
    protected Service service;

    public abstract void handleRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public void setModel (Service service) {
        this.service = service;
    }
}
