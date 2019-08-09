package ua.gladiator.model.dao.mapper;

import ua.gladiator.model.entity.Book;
import ua.gladiator.model.entity.builders.BookBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper {
    public static Book extractFromResultSet(ResultSet rs) throws SQLException {
        return BookBuilder
                .builder()
                .buildAddDate(rs.getDate("add_date").toLocalDate())
                .buildAttributesWithString(rs.getString("attributes"))
                .buildName(rs.getString("name"))
                .buildAuthor(rs.getString("author"))
                .buildDaysToReturn(rs.getInt("days_to_return"))
                .buildText(rs.getString("text"))
                .buildPicUrl(rs.getString("pic_url"))
                .buildId(rs.getLong("book_id"))
                .buildIsAvailable(rs.getBoolean("is_available"))
                .build();
    }
}
