package ua.gladiator.model.dao.impl;

import org.apache.log4j.Logger;
import ua.gladiator.model.dao.BookDao;
import ua.gladiator.model.dao.mapper.BookMapper;
import ua.gladiator.model.entity.Book;

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
            log.error("bookDao create " + e);
            e.printStackTrace();
        }
//todo check date
    }

    @Override
    public void addAttribute(String bookName, String attribute) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.addatt"))) {
            ps.setString(1, bookName);
            ps.setString(2, attribute);

            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("bookDao addAttribute " + e);
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Boolean isUnique(String name) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.isunique"))) {
            ps.setString(1, name);

            resultSet = ps.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            log.error("bookDao isUnique " + e);
            e.printStackTrace();
        }
        throw new RuntimeException();
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
            log.error("bookDao findById " + e);
            e.printStackTrace();
            throw new RuntimeException();
        }
        return book;
    }

    @Override
    public void deleteBooksAttributes(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.deleteatt"))) {
            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            log.error("BookDao deleteBooksAttributes " + e);
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.delete"))) {
            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            log.error("BookDao delete " + e);
            e.printStackTrace();
            throw new RuntimeException();
        }

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
            log.error("bookDao findAll " + e);
            e.printStackTrace();
            throw new RuntimeException();
        }
        return books;
    }

    public List<Book> findByParams(String attribute, String author, String line) {
            return null;
    }

    @Override
    public void setAvailable(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.setavailable"))) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            log.error("BookDao setAvailable " + e);
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Book> findByParams(List<String> attributes, String author, String line, Integer startingEl, Integer pageSize) {
        String query = createParamsQuery(attributes.size(), "book.findbyparamsstart", "book.findbyparamsend");

        List<Book> books = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query))
                 {

            ps.setString(1, "%" + Objects.toString(line, "") + "%");
            ps.setString(2, "%" + Objects.toString(line, "") + "%");
            ps.setString(3, "%" + Objects.toString(author, "") + "%");
            for (int i = 0; i < attributes.size(); i++) {
                ps.setString(i + 4, attributes.get(i));
            }

            ps.setInt(4 + attributes.size(), startingEl);
            ps.setInt(5 + attributes.size(), pageSize);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                books.add(BookMapper.extractFromResultSet(resultSet));
            }
        } catch (Exception e) {
            log.error("BookDao findByParams " + e);
            e.printStackTrace();
            throw new RuntimeException();
        }
        return books;
    }

    /**
     * creates query for different number of attributes
     * for example for 3 params query will be : WHERE attribute IN (?, ?, ?)
     */
    private String createParamsQuery(int size, String keyStart, String keyEnd) {
        StringBuilder sb = new StringBuilder(rb.getString(keyStart) + "?");
        for (int i = 1; i < size; i++) {
            sb.append(", ?");
        }
        sb.append(rb.getString(keyEnd));
        return sb.toString();
    }

    @Override
    public Integer countByParams(List<String> attributes, String author, String line) {
        String query = createParamsQuery(attributes.size(), "book.countbyparamsstart", "book.countbyparamsend");

        Integer count = 0;
        try (PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setString(1, "%" + Objects.toString(line, "") + "%");
            ps.setString(2, "%" + Objects.toString(line, "") + "%");
            ps.setString(3, "%" + Objects.toString(author, "") + "%");
            for (int i = 0; i < attributes.size(); i++) {
                ps.setString(i + 4, attributes.get(i));
            }

            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            log.error("BookDao countBooks " + e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    public void setUnavailable(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("book.setunavailable"))) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            log.error("BookDao setUnavailable " + e);
            e.printStackTrace();
            throw new RuntimeException();
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
            log.error("BookDao close " + e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
