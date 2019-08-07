package main.java.ua.gladiator.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GenericMapper<T> {
    T extractFromResultSet(ResultSet resultSet) throws SQLException;
}
