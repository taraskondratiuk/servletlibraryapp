package main.java.ua.gladiator.model.entity.builders;

import main.java.ua.gladiator.model.entity.User;
import main.java.ua.gladiator.model.entity.enums.Role;


public final class UserBuilder {
    private Long id;
    private String email;
    private Integer phoneNumber;
    private String password;
    private Role role;

    private UserBuilder() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder buildEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder buildPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder buildPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder buildRole(Role role) {
        this.role = role;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
}
