package ua.gladiator.controller;

import ua.gladiator.controller.command.Command;
import ua.gladiator.controller.command.CommandInitializer;
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


public class MainController extends HttpServlet{

/*    private AttributeService attributeService = new AttributeServiceImpl();

    private TakeService takeService = new TakeServiceImpl();

    private BookService bookService = new BookServiceImpl();

    private UserService userService = new UserServiceImpl();
    */


    private Map<String, Command> commands = new HashMap<>();
    @Override
    public void init() {
        commands = CommandInitializer.getCommands();
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        System.out.println("servlet work1");


        String path = req.getRequestURI();
        System.out.println(path);
        //path = path.replaceAll("\\.jsp" , "");
        path = path.replaceAll(".*/lib/","");
        //path = path.replaceAll("\\.jsp","");
        Command command = commands.getOrDefault(path ,
                (r)->"redirect/error.jsp");

        String page = command.execute(req);
        System.out.println("LSDLKFJLKSDGJ" + page);

        if(page.contains("redirect")){
            resp.sendRedirect(page.replace("redirect/", ""));
        }else {
            req.getRequestDispatcher(page).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
