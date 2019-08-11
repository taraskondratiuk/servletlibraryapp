package ua.gladiator.controller.command;

import ua.gladiator.model.entity.dto.Page;
import ua.gladiator.model.service.TakeService;
import ua.gladiator.model.service.impl.TakeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class GetTakesAdmin implements Command {
    private TakeService takeService = new TakeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();

        req.setAttribute("page", takeService.getFilteredTakes(
                params.getOrDefault("returned", new String[]{""})[0],
                null,
                params.getOrDefault("email", new String[]{""})[0],
                Integer.parseInt(params.getOrDefault("page", new String[]{"1"})[0]))
        );

        return "takes.jsp";
    }
}
