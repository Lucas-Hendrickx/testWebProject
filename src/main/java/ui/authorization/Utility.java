package ui.authorization;

import domain.model.Registered;
import domain.model.Role;
import javax.servlet.http.HttpServletRequest;

public class Utility {

    /**
     * Checks if the role of the registered stored in the session has one of the given roles
     * @throws NotAuthorizedException if the role does not correspond with one of the given roles
     */
    public static void checkRole(HttpServletRequest request, Role[] roles) {
        boolean found = false;
        Registered registered = (Registered) request.getSession().getAttribute("registered");
        if (registered != null) {
            for (Role role: roles) {
                if (registered.getRole().equals(role)) found = true;
            }
        }
        if (!found) throw new NotAuthorizedException();
    }

}
