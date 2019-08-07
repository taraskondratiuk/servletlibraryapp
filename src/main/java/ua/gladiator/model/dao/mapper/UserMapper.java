package main.java.ua.gladiator.model.dao.mapper;

import main.java.ua.gladiator.model.entity.User;
import main.java.ua.gladiator.model.entity.builders.UserBuilder;
import main.java.ua.gladiator.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements GenericMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return UserBuilder
                .builder()
                .buildEmail(rs.getString("email"))
                .buildId(rs.getLong("user_id"))
                .buildPassword(rs.getString("password"))
                .buildPhoneNumber(rs.getInt("phone_number"))
                .buildRole(Role.valueOf(rs.getString("role_name")))
                .build();
    }
}
