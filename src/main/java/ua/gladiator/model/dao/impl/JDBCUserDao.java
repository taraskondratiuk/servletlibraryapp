package ua.gladiator.model.dao.impl;

import ua.gladiator.model.dao.UserDao;
import ua.gladiator.model.dao.mapper.BookMapper;
import ua.gladiator.model.dao.mapper.UserMapper;
import ua.gladiator.model.entity.User;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private static ResourceBundle rb = ResourceBundle.getBundle("properties.db", new Locale("en", "US"));
    private Connection connection;
    private ResultSet resultSet;


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("user.create"))) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setInt(3, entity.getPhoneNumber());
            ps.setInt(4, entity.getCountryCode());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("user.findbyid"))) {

            ps.setLong(1, id);

            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(UserMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        final String sql = rb.getString("user.findall");

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                users.add(UserMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User entity) {

    }

    public void setReaderRole(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("user.setreaderrole"))) {
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("user.findbyemail"))) {

            ps.setString(1, email);

            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(UserMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
