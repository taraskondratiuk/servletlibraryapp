package ua.gladiator.model.service.impl;

import ua.gladiator.model.dao.AttributeDao;
import ua.gladiator.model.dao.DaoFactory;
import ua.gladiator.model.entity.Attribute;
import ua.gladiator.model.service.AttributeService;

import java.util.*;

public class AttributeServiceImpl implements AttributeService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private AttributeDao attributeDao;

    @Override
    public List<Attribute> getAllAttributes() {
        attributeDao = daoFactory.createAttributeDao();
        List<Attribute> attributes = attributeDao.findAll();
        attributeDao.close();
        return attributes;
    }

    @Override
    public Attribute createAttribute(Attribute attribute) {
        attributeDao = daoFactory.createAttributeDao();
        attributeDao.create(attribute);
        attributeDao.close();
        return attribute;
    }
}
