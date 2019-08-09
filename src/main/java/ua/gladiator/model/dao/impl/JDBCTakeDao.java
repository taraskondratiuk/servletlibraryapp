package ua.gladiator.model.dao.impl;

import org.apache.log4j.Logger;
import ua.gladiator.model.dao.TakeDao;
import ua.gladiator.model.dao.mapper.TakeMapper;
import ua.gladiator.model.entity.Take;

import java.sql.*;
import java.util.*;

public class JDBCTakeDao implements TakeDao {
    private static ResourceBundle rb = ResourceBundle.getBundle("properties.db", new Locale("en", "US"));
    private Connection connection;
    private ResultSet resultSet;


    private static final Logger log = Logger.getLogger(JDBCTakeDao.class);


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
            log.trace(e);
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
            log.trace(e);
            e.printStackTrace();
        }
        return take;
    }

    @Override
    public List<Take> findAll(Integer page) {
        return null;
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
            log.trace(e);
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
            log.trace(e);
            e.printStackTrace();
        }
    }

    //todo test
    public List<Take> findByParams(Boolean isReturned, String email, Long userId, Integer startingEl, Integer pageSize) {
        List<Take> takes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("take.findbyparams"))) {

            if (isReturned == null) {
                ps.setNull(1, 0);
                ps.setNull(2, 0);
            } else {
                ps.setBoolean(2, isReturned);
                ps.setBoolean(1, isReturned);
            }

            ps.setString(3,"%" + Objects.toString(email, "") + "%");
            ps.setString(4,"%" + Objects.toString(email, "") + "%");

            ps.setString(5, Objects.toString(userId, ""));
            ps.setString(6, Objects.toString(userId, ""));

            ps.setInt(7, startingEl);
            ps.setInt(8, pageSize);

            System.out.println(ps);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                takes.add(TakeMapper.extractFromResultSet(resultSet));
            }
        } catch (Exception e) {
            log.trace(e);
            e.printStackTrace();
        }
        return takes;
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
