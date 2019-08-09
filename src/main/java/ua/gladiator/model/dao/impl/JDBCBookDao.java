package ua.gladiator.model.dao.impl;

import org.apache.log4j.Logger;
import ua.gladiator.controller.AccountsController;
import ua.gladiator.model.dao.BookDao;
import ua.gladiator.model.dao.mapper.AttributeMapper;
import ua.gladiator.model.dao.mapper.BookMapper;
import ua.gladiator.model.dao.mapper.TakeMapper;
import ua.gladiator.model.entity.Book;
import ua.gladiator.model.dao.BookDao;
import ua.gladiator.model.dao.mapper.BookMapper;
import ua.gladiator.model.entity.Take;

import java.sql.*;
import java.util.*;

public class JDBCBookDao implements BookDao {
    private Connection connection;
    private ResultSet resultSet;

    private static ResourceBundle rb = ResourceBundle.getBundle("properties.db", new Locale("en", "US"));

    private static final Logger log = Logger.getLogger(BookDao.class);

    public JDBCBookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Book entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.create"))) {
            ps.setInt(1, entity.getDaysToReturn());
            ps.setString(2, entity.getText());
            ps.setDate(3, java.sql.Date.valueOf(entity.getAddDate()));
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getAuthor());
            ps.setString(6, entity.getPicUrl());
            ps.setBoolean(7, entity.getAvailable());

            ps.executeUpdate();
        } catch (SQLException e) {
            log.trace(e);
            e.printStackTrace();
        }
//todo check date
    }

    @Override
    public Optional<Book> findById(Long id) {
        Optional<Book> book = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.findbyid"))) {

            ps.setLong(1, id);

            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                book = Optional.of(BookMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.trace(e);
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.delete"))) {
            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            log.trace(e);
            e.printStackTrace();
        }

    }

    @Override
    public List<Book> findAll(Integer page) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        final String sql = rb.getString("book.findall");

        try (Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                books.add(BookMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.trace(e);
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> findByParams(String attribute, String author, String line) {
            return null;
    }

    public void setAvailable(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.setavailable"))) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            log.trace(e);
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findByParams(String attribute, String author, String line, Integer startingEl, Integer pageSize) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.findbyparams"))) {

            ps.setString(1, "%" + Objects.toString(line, "") + "%");
            ps.setString(2, "%" + Objects.toString(line, "") + "%");
            ps.setString(3, "%" + Objects.toString(author, "") + "%");
            ps.setString(4, "%" + Objects.toString(attribute, "") + "%");
            ps.setInt(5, startingEl);
            ps.setInt(6, pageSize);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                books.add(BookMapper.extractFromResultSet(resultSet));
            }
        } catch (Exception e) {
            log.trace(e);
            e.printStackTrace();
        }
        return books;
    }

    public void setUnavailable(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.setunavailable"))) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            log.trace(e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.trace(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
