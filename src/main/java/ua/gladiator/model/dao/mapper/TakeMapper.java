package ua.gladiator.model.dao.mapper;

import ua.gladiator.model.entity.Take;
import ua.gladiator.model.entity.builders.TakeBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TakeMapper {
    public static Take extractFromResultSet(ResultSet rs) throws SQLException {
        return TakeBuilder
                .builder()
                .buildBookAuthor(rs.getString("author"))
                .buildBookName(rs.getString("name"))
                .buildBookPicUrl(rs.getString("pic_url"))
                .buildReturnDate(rs.getDate("return_date"))
                .buildTakeDate(rs.getDate("take_date").toLocalDate())
                .buildReturnDeadline(rs.getDate("return_deadline").toLocalDate())
                .buildId(rs.getLong("take_id"))
                .buildUserEmail(rs.getString("email"))
                .buildUserPhone(rs.getInt("phone_number"))
                .buildBookId(rs.getLong("book_id"))
                .buildUserId(rs.getLong("user_id"))
                .buildIsReturned(rs.getBoolean("is_returned"))
                .build();
    }
}
