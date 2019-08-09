package ua.gladiator.model.service;

import ua.gladiator.model.entity.User;

import java.util.*;

public interface UserService {
    User getCurrentUser();
    Optional<User> getUserByEmail(String email);
    User registerUser(User user);
}
