package ua.gladiator.model.dao.impl;

import ua.gladiator.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public AttributeDao createAttributeDao() {
        return new JDBCAttributeDao(getConnection());
    }

    @Override
    public BookDao createBookDao() {
        return new JDBCBookDao(getConnection());
    }

    @Override
    public TakeDao createTakeDao() {
        return new JDBCTakeDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
