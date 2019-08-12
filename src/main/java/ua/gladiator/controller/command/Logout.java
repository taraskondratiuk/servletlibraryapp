package ua.gladiator.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "redirect/index?logout=success";
    }
}
