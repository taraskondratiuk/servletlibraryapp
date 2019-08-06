package main.java.ua.gladiator.model.dao.impl;

import main.java.ua.gladiator.model.dao.UserDao;
import main.java.ua.gladiator.model.entity.User;

import java.sql.Connection;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void close() {

    }
}
