package ua.gladiator.model.dao;

import ua.gladiator.model.entity.Book;

import java.util.*;

public interface BookDao extends GenericDao<Book> {
    List<Book> findByParams(String attribute, String author, String line, Integer startingEl, Integer pageSize);
    public void setUnavailable(Long id);
}
