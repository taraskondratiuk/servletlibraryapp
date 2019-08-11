package ua.gladiator.model.dao;

import ua.gladiator.model.entity.Attribute;

import java.util.*;

public interface AttributeDao extends GenericDao<Attribute> {
    Optional<Attribute> findByName(String name);
}
