package ua.gladiator.model.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ua.gladiator.model.dao.DaoFactory;
import ua.gladiator.model.dao.TakeDao;
import ua.gladiator.model.dao.UserDao;
import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.builders.UserBuilder;
import ua.gladiator.model.entity.enums.Role;
import ua.gladiator.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao;

    @Override
    public User getCurrentUser(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("user");
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
        Long id = userDao.findIdByEmail(user.getEmail());
        userDao.setReaderRole(id);
        userDao.close();
        return user;

    }

    @Override
    public Boolean isRegistered(String email) {
        userDao = daoFactory.createUserDao();

        Optional<User> user = userDao.findByEmail(email);
        userDao.close();
        return user.isPresent();
    }


    @Override
    public Boolean isPasswordRight(String email, String pw) {
        userDao = daoFactory.createUserDao();
        Optional<User> user = userDao.findByEmail(email);

         boolean isVerified = false;
         if (user.isPresent()) {
             isVerified = BCrypt.verifyer().verify(pw.toCharArray(), user.get().getPassword().toCharArray()).verified;
         }
        userDao.close();
        return isVerified;
    }
}
