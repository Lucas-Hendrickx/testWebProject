package ui.controller;

import domain.model.CoronaTest;
import domain.model.Role;
import domain.model.User;
import ui.authorization.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Create_CoronaTest extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.GUARDIAN};
        Utility.checkRole(request, roles);

        List<String> result = new ArrayList<>();
        CoronaTest coronatest = new CoronaTest();

        setuserId(coronatest, request, result);
        setDateOfTest(coronatest, request, result);

        if (result.size() > 0) {
            request.setAttribute("result", result);
            request.getRequestDispatcher("Controller?command=Open_CoronaTest").forward(request, response);
        } else {
            service.addCoronaTest(coronatest);
            request.setAttribute("success", "The Corona test is successfully added.");
            request.getRequestDispatcher("Controller?command=Open_CoronaTest").forward(request, response);
        }
    }


    /***
     * Setters
     */
    private void setuserId(CoronaTest coronatest, HttpServletRequest request, List<String> result) {
        String fullname = request.getParameter("user");
        String[] namesplit = request.getParameter("user").split(" ");
        User user = service.getUser((String) request.getSession().getAttribute("email"), namesplit[0], namesplit[1]);

        try {
            coronatest.setUserId(user.getUserId());
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setDateOfTest(CoronaTest coronatest, HttpServletRequest request, List<String> result) {
        String date = request.getParameter("date");
        request.setAttribute("dateReturn", date);
        String time = request.getParameter("time");
        request.setAttribute("timeReturn", time);
        try {
            coronatest.setDateOfTestFromString(date, time);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

}
