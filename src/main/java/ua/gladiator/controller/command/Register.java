package ua.gladiator.controller.command;

import ua.gladiator.model.entity.builders.UserBuilder;
import ua.gladiator.model.entity.enums.Role;
import ua.gladiator.model.service.UserService;
import ua.gladiator.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class Register implements Command {
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        if (req.getParameter("email") == null) {
            return "register.jsp";
        }
        if (userService.isRegistered(req.getParameter("email"))) {
            req.setAttribute("status", "taken");
            return "register.jsp";
        }
        userService.registerUser(UserBuilder
                .builder()
                .buildCountryCode(Integer.valueOf(req.getParameter("coCode")))
                .buildPhoneNumber(Integer.valueOf(req.getParameter("phone")))
                .buildPassword(req.getParameter("password"))
                .buildEmail(req.getParameter("email"))
                .buildRole(Role.READER)
                .build());
        return "redirect/index?status=registered";
    }
}
