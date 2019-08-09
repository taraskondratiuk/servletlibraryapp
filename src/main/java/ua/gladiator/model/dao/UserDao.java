package ua.gladiator.model.dao;

import ua.gladiator.model.entity.User;

import java.util.*;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);
}
