package ua.gladiator.controller;

import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.builders.UserBuilder;
import ua.gladiator.model.entity.enums.Role;
import ua.gladiator.model.service.AttributeService;
import ua.gladiator.model.service.BookService;
import ua.gladiator.model.service.TakeService;
import ua.gladiator.model.service.UserService;
import ua.gladiator.model.service.impl.AttributeServiceImpl;
import ua.gladiator.model.service.impl.BookServiceImpl;
import ua.gladiator.model.service.impl.TakeServiceImpl;
import ua.gladiator.model.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class MainController extends HttpServlet {

    private AttributeService attributeService = new AttributeServiceImpl();

    private TakeService takeService = new TakeServiceImpl();

    private BookService bookService = new BookServiceImpl();

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        System.out.println(path);

      /*  User user = UserBuilder
                .builder()
                .buildEmail("heyyou@the.wall")
                .buildPassword("password")
                .buildPhoneNumber(999259622)
                .buildRole(Role.READER)
                .buildCountryCode(380)
                .build();*/




        System.out.println(123);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
