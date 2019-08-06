package main.java.ua.gladiator.model.dao.impl;

import main.java.ua.gladiator.model.dao.BookDao;
import main.java.ua.gladiator.model.entity.Book;

import java.sql.Connection;
import java.util.*;

public class JDBCBookDao implements BookDao {
    private Connection connection;

    public JDBCBookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Book entity) {

    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void close() {

    }
}
