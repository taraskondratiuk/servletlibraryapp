package ua.gladiator.model.dao.impl;

import ua.gladiator.model.dao.TakeDao;
import ua.gladiator.model.dao.mapper.TakeMapper;
import ua.gladiator.model.entity.Take;

import java.sql.*;
import java.util.*;

public class JDBCTakeDao implements TakeDao {
    private static ResourceBundle rb = ResourceBundle.getBundle("properties.db", new Locale("en", "US"));
    private Connection connection;
    private ResultSet resultSet;


    public JDBCTakeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Take entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("take.create"))) {
            ps.setDate(1, java.sql.Date.valueOf(entity.getReturnDeadline()));
            ps.setLong(2, entity.getBookId());
            ps.setLong(3, entity.getUserId());
            ps.setDate(4, java.sql.Date.valueOf(entity.getReturnDate()));
            ps.setDate(5, java.sql.Date.valueOf(entity.getTakeDate()));


            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Take> findById(Long id) {
        Optional<Take> take = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("take.findbyid"))) {

            ps.setLong(1, id);

            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                take = Optional.of(TakeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return take;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Take> findAll() {
        List<Take> takes = new ArrayList<>();
        final String sql = rb.getString("att.findall");

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                takes.add(TakeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return takes;
    }

    @Override
    public void update(Take entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("take.update"))) {
            ps.setDate(1, java.sql.Date.valueOf(entity.getReturnDeadline()));
            ps.setLong(2, entity.getBookId());
            ps.setLong(3, entity.getUserId());
            ps.setDate(4, java.sql.Date.valueOf(entity.getReturnDate()));
            ps.setDate(5, java.sql.Date.valueOf(entity.getTakeDate()));
            ps.setBoolean(7, entity.getReturned());
            ps.setLong(6, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //todo test
    public List<Take> findByParams(Boolean isReturned, String email, Long userId) {
        List<Take> takes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("take.findbyparams"))) {

            if (isReturned != null) {
                ps.setBoolean(1, isReturned);
                ps.setBoolean(2, isReturned);
            } else {

                ps.setNull(1, 0);
                ps.setNull(2, 0);
            }

            if (email != null) {

                ps.setString(3,"%" + email + "%");
                ps.setString(4, "%" + email + "%");
            } else {
                ps.setNull(3, 0);
                ps.setNull(4, 0);
            }
            if (userId != null) {
                ps.setLong(5, userId);
                ps.setLong(6, userId);
            } else {
                ps.setNull(5, 0);
                ps.setNull(6, 0);

            }
            System.out.println(ps);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                takes.add(TakeMapper.extractFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return takes;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
