package ua.gladiator.controller.command;

import ua.gladiator.model.entity.builders.AttributeBuilder;
import ua.gladiator.model.entity.builders.BookBuilder;
import ua.gladiator.model.service.AttributeService;
import ua.gladiator.model.service.impl.AttributeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

public class CreateAttribute implements Command {
    private AttributeService attributeService = new AttributeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        Map<String, String[]> params = req.getParameterMap();

        if (attributeService.checkIfExists(req.getParameter("addAtt"))) {
            return "redirect/books?error=errorAtt";
        }
        attributeService.createAttribute(AttributeBuilder.builder().buildName(req.getParameter("addAtt")).build());


        return "redirect/books?createAtt=success";
    }
}
