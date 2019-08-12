package ua.gladiator.controller.command;

import ua.gladiator.model.entity.Take;
import ua.gladiator.model.service.TakeService;
import ua.gladiator.model.service.UserService;
import ua.gladiator.model.service.impl.TakeServiceImpl;
import ua.gladiator.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class GetTakesReader implements Command {
    private TakeService takeService = new TakeServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();

        req.setAttribute("page", takeService.getFilteredTakes(
                params.getOrDefault("returned", new String[]{""})[0],
                userService.getCurrentUser(req).getId(),
                "",
                Integer.parseInt(params.getOrDefault("page", new String[]{"1"})[0]))
        );

        return "takes.jsp";
    }

}
