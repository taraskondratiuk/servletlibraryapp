package ua.gladiator.controller.command;

import ua.gladiator.model.entity.User;
import ua.gladiator.model.service.UserService;
import ua.gladiator.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    private UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest req) {

        String email = req.getParameter("email");

        if (email == null) {
            return "login.jsp";
        }
        if (userService.isPasswordRight(email, req.getParameter("password"))) {
            User user = userService.getUserByEmail(email).get();
            req.getSession().setAttribute("user", user);
            String role = userService.getCurrentUser(req).getRole().toString().toLowerCase();
            return "redirect/" + role + "/books";
        }

        req.setAttribute("status", "wrongLogin");
        return "login.jsp";
    }
}
