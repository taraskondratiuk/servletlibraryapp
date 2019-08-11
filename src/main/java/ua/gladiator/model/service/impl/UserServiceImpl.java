package ua.gladiator.model.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ua.gladiator.model.dao.DaoFactory;
import ua.gladiator.model.dao.TakeDao;
import ua.gladiator.model.dao.UserDao;
import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.builders.UserBuilder;
import ua.gladiator.model.service.UserService;

import java.util.*;

public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao;

    @Override
    public User getCurrentUser() {
        return UserBuilder.builder()
                .buildEmail("sdfkj;")
                .buildPhoneNumber(34223)
                .buildId(4L)
                .build();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        userDao = daoFactory.createUserDao();

        Optional<User> user = userDao.findByEmail(email);

        userDao.close();
        return user;
    }

    @Override
    public User registerUser(User user) {
        userDao = daoFactory.createUserDao();

        String pw = user.getPassword();

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, pw.toCharArray());

        user.setPassword(bcryptHashString);
        userDao.create(user);
        userDao.close();
        return user;

    }
}
