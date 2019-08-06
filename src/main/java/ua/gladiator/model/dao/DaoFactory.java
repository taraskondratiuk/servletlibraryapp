package main.java.ua.gladiator.model.dao;

import main.java.ua.gladiator.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    public abstract AttributeDao createAttributeDao();
    public abstract BookDao createBookDao();
    public abstract TakeDao createTakeDao();
    public abstract UserDao createUserDao();

    public static  DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
