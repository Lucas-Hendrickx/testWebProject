package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Remove extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();
        String userId = request.getParameter("userid");
        request.setAttribute("loginId", userId);

        List<Person> personList = service.getAll();
        Person thePerson = null;

        for (Person person :  personList) {
            if (person.getUserid().equals(userId)) {
                thePerson = person;
            }
        }

        if (thePerson == null) {
            result.add("Userid does not exist.");
            request.setAttribute("result", result);
            return "remove.jsp";
        } else {
            service.delete(userId);
            return "Controller?command=Overview";
        }

    }
}
