package main.java.ua.gladiator.model.dao.impl;

import main.java.ua.gladiator.model.dao.*;
import org.omg.CosNaming.NamingContextPackage.CannotProceedHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceedHolder;

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
