package ua.gladiator.model.dao.mapper;

import ua.gladiator.model.entity.Attribute;
import ua.gladiator.model.entity.builders.AttributeBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeMapper {
    public static Attribute extractFromResultSet(ResultSet rs) throws SQLException {
        return AttributeBuilder
                .builder()
                .buildId(rs.getLong("attribute_id"))
                .buildName(rs.getString("attribute"))
                .build();
    }
}
