package ua.gladiator.controller.command;

import ua.gladiator.model.service.AttributeService;
import ua.gladiator.model.service.BookService;
import ua.gladiator.model.service.impl.AttributeServiceImpl;
import ua.gladiator.model.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class GetBooks implements Command {

    private BookService bookService = new BookServiceImpl();
    private AttributeService attributeService = new AttributeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();

        req.setAttribute("attributes", attributeService.getAllAttributes());
        req.setAttribute("page", bookService.getBooksByParams(
                params.getOrDefault("attributes", new String[]{""}),
                params.getOrDefault("line", new String[1])[0],
                params.getOrDefault("author",  new String[1])[0],
                Integer.valueOf(params.getOrDefault("page", new String[]{"1"})[0])));

        return "books.jsp";
    }
}
