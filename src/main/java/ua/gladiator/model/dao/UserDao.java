package ua.gladiator.model.dao;

import ua.gladiator.model.entity.User;

import java.util.*;

public interface UserDao extends GenericDao<User> {
    void setReaderRole(Long id);


    Long findIdByEmail(String email);

    Optional<User> findByEmail(String email);
}
