package ua.gladiator.model.dao.mapper;

import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.builders.UserBuilder;
import ua.gladiator.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static User extractFromResultSet(ResultSet rs) throws SQLException {
        return UserBuilder
                .builder()
                .buildEmail(rs.getString("email"))
                .buildId(rs.getLong("user_id"))
                .buildPassword(rs.getString("password"))
                .buildPhoneNumber(rs.getInt("phone_number"))
                .buildCountryCode(rs.getInt("country_code"))
                .buildRole(Role.valueOf(rs.getString("role_name")))
                .build();
    }
}
