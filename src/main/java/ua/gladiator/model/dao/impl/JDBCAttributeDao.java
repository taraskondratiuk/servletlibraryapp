package ua.gladiator.model.dao.impl;

import ua.gladiator.model.dao.AttributeDao;
import ua.gladiator.model.dao.mapper.AttributeMapper;
import ua.gladiator.model.entity.Attribute;
import ua.gladiator.model.dao.AttributeDao;

import java.sql.*;
import java.util.*;

public class JDBCAttributeDao implements AttributeDao {
    private Connection connection;
    private ResultSet resultSet;

    private static ResourceBundle rb = ResourceBundle.getBundle("properties.db", new Locale("en", "US"));

    public JDBCAttributeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Attribute entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("att.create"))) {
            ps.setString(1, entity.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<Attribute> findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Attribute> findAll() {
        List<Attribute> attributes = new ArrayList<>();
        final String sql = rb.getString("att.findall");

        try (Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                attributes.add(AttributeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    @Override
    public Optional<Attribute> findByName(String name) {
        try (PreparedStatement ps = connection.prepareStatement(
                rb.getString("att.findbyname"))) {

            ps.setString(1, name);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return Optional.of(AttributeMapper.extractFromResultSet(resultSet));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Attribute entity) {

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
