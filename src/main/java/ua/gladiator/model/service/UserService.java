package ua.gladiator.model.service;

import ua.gladiator.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public interface UserService {
    User getCurrentUser(HttpServletRequest req);

    Optional<User> getUserByEmail(String email);
    User registerUser(User user);

    Boolean isRegistered(String email);

    Boolean isPasswordRight(String email, String pw);
}
