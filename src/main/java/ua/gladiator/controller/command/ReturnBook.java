package ua.gladiator.controller.command;

import ua.gladiator.model.service.TakeService;
import ua.gladiator.model.service.UserService;
import ua.gladiator.model.service.impl.TakeServiceImpl;
import ua.gladiator.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ReturnBook implements Command {
    private TakeService takeService = new TakeServiceImpl();
    @Override
    public String execute(HttpServletRequest req) {
        takeService.makeTakeReturned(Long.valueOf(req.getParameter("takeId")));
        return "redirect/takes?return=success";
    }
}
