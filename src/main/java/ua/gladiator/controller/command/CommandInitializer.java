package ua.gladiator.controller.command;

import java.util.*;

public class CommandInitializer {
    public static Map<String, Command> getCommands() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("admin/books", new GetBooks());
        commandMap.put("reader/books", new GetBooks());
        commandMap.put("admin/addBook", new CreateBook());
        commandMap.put("admin/addAtt", new CreateAttribute());
        commandMap.put("admin/deleteBook", new DeleteBook());
        commandMap.put("admin/takes", new GetTakesAdmin());
        commandMap.put("reader/takes", new GetTakesReader());
        commandMap.put("reader/takeBook", new TakeBook());
        commandMap.put("reader/returnBook", new ReturnBook());
        commandMap.put("index", new Index());
        commandMap.put("login", new Login());
        commandMap.put("register", new Register());
        commandMap.put("logout", new Logout());

        return commandMap;
    }
}
