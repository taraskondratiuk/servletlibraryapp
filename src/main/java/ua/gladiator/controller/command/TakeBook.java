package ua.gladiator.controller.command;

import ua.gladiator.model.service.BookService;
import ua.gladiator.model.service.UserService;
import ua.gladiator.model.service.impl.BookServiceImpl;
import ua.gladiator.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class TakeBook implements Command {
    private BookService bookService = new BookServiceImpl();
    private UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest req) {
        bookService.takeBook(userService.getCurrentUser(), Long.valueOf(req.getParameter("bookId")));
        return "redirect/books?take=success";
    }
}
