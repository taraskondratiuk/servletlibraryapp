package main.java.ua.gladiator.model.dao.impl;

import main.java.ua.gladiator.model.dao.AttributeDao;
import main.java.ua.gladiator.model.entity.Attribute;

import java.sql.Connection;
import java.util.*;

public class JDBCAttributeDao implements AttributeDao {
    private Connection connection;

    public JDBCAttributeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Attribute entity) {

    }

    @Override
    public Attribute findById(Long id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Attribute> findAll() {
        return null;
    }

    @Override
    public void update(Attribute entity) {

    }

    @Override
    public void close() {

    }
}
