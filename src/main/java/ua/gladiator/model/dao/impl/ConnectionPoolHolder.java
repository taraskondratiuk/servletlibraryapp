package main.java.ua.gladiator.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.*;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private static ResourceBundle rb = ResourceBundle.getBundle("properties.db", new Locale("en", "US"));

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(rb.getString("db.url"));
                    ds.setUsername(rb.getString("db.username"));
                    ds.setPassword(rb.getString("db.password"));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    dataSource = ds;

                }
            }
        }
        return dataSource;
    }
}
