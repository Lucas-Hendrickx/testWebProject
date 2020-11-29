package ui.controller;

import domain.model.Role;
import ui.authorization.Utility;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Function_Logout extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.ADMIN, Role.GUARDIAN};
        Utility.checkRole(request, roles);

        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }
}
