package ua.gladiator.model.service;

import ua.gladiator.model.entity.Attribute;

import java.util.*;

public interface AttributeService {
    List<Attribute> getAllAttributes();
    Attribute createAttribute(Attribute attribute);

    Boolean checkIfExists(String name);
}
