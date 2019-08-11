package ua.gladiator.model.dao;

import ua.gladiator.model.entity.Book;

import java.util.*;

public interface BookDao extends GenericDao<Book> {
    void addAttribute(String bookName, String attribute);

    Boolean isUnique(String name);

    void deleteBooksAttributes(Long id);

    void setAvailable(Long id);

    List<Book> findByParams(List<String> attributes, String author, String line, Integer startingEl, Integer pageSize);

    Integer countByParams(List<String> attributes, String author, String line);

    void setUnavailable(Long id);
}