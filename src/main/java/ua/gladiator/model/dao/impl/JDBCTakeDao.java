package main.java.ua.gladiator.model.dao.impl;

import main.java.ua.gladiator.model.dao.TakeDao;
import main.java.ua.gladiator.model.entity.Take;

import java.sql.Connection;
import java.util.*;

public class JDBCTakeDao implements TakeDao {
    private Connection connection;

    public JDBCTakeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Take entity) {

    }

    @Override
    public Take findById(Long id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Take> findAll() {
        return null;
    }

    @Override
    public void update(Take entity) {

    }

    @Override
    public void close() {

    }
}
