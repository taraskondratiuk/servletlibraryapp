package ua.gladiator.controller.command;

import ua.gladiator.model.service.BookService;
import ua.gladiator.model.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class DeleteBook implements Command {
    private BookService bookService = new BookServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        bookService.deleteBook(Long.valueOf(req.getParameter("bookId")));
        return "redirect/books?delete=success";
    }
}
