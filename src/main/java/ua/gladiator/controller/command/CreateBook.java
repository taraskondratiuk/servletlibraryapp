package ua.gladiator.controller.command;

import ua.gladiator.model.entity.builders.BookBuilder;
import ua.gladiator.model.service.BookService;
import ua.gladiator.model.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

public class CreateBook implements Command {
    private BookService bookService = new BookServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        Map<String, String[]> params = req.getParameterMap();

        if (!bookService.isNameUnique(params.get("name")[0])) {
            return "redirect/books&error=errorBook";
        }
        bookService.addBook(BookBuilder.builder()
                .buildAddDate(LocalDate.now())
                .buildAttributes(Arrays.asList(params.get("addAttributes")))
                .buildIsAvailable(true)
                .buildAuthor(params.getOrDefault("author", null)[0])
                .buildDaysToReturn(Integer.parseInt(params.get("daysToReturn")[0]))
                .buildName(params.get("name")[0])
                .buildPicUrl(params.get("picUrl")[0])
                .buildText(params.get("text")[0])
                .build());



        return "redirect/books?createBook=success";
    }
}
