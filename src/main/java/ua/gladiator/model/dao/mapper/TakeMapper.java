package main.java.ua.gladiator.model.dao.mapper;

import main.java.ua.gladiator.model.entity.Take;
import main.java.ua.gladiator.model.entity.builders.TakeBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TakeMapper implements GenericMapper<Take> {
    @Override
    public Take extractFromResultSet(ResultSet rs) throws SQLException {
        return TakeBuilder
                .builder()
                .buildBookAuthor(rs.getString("author"))
                .buildBookName(rs.getString("name"))
                .buildBookPicUrl(rs.getString("pic_url"))
                .buildReturnDate(rs.getDate("return_date").toLocalDate())
                .buildTakeDate(rs.getDate("return_date").toLocalDate())
                .buildReturnDeadline(rs.getDate("return_deadline").toLocalDate())
                .buildId(rs.getLong("take_id"))
                .buildUserEmail(rs.getString("email"))
                .buildUserPhone(rs.getInt("phone_number"))
                .build();
    }
}
