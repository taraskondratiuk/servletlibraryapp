package main.java.ua.gladiator.model.dao.mapper;

import main.java.ua.gladiator.model.entity.Attribute;
import main.java.ua.gladiator.model.entity.builders.AttributeBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeMapper implements GenericMapper<Attribute> {
    @Override
    public Attribute extractFromResultSet(ResultSet rs) throws SQLException {
        return AttributeBuilder
                .builder()
                .buildId(rs.getLong("attribute_id"))
                .buildName(rs.getString("attribute"))
                .build();
    }
}
