package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Change extends RequestHandler{
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<String> result = new ArrayList<String>();

        String userid = session.getId();
        Person person = service.get(userid);

        String password = request.getParameter("changePassword");
        String passwordRepeat = request.getParameter("passwordRepeat");

        checkPassword(result, password, passwordRepeat);

        if (result.size() > 0) {
            request.setAttribute("result", result);
            return "index.jsp";
        }

        person.setPassword(password);
        return "index.jsp";
    }

    private void checkPassword(List<String> result, String password, String passwordRepeat) {
        if (!password.equals(passwordRepeat)) result.add("Password don't match.");
    }
}
