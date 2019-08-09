package ua.gladiator.model.dao;

import java.util.*;

public interface GenericDao<T> extends AutoCloseable{
    void create(T entity);
    Optional<T> findById(Long id);
    void delete(Long id);
    List<T> findAll(Integer page);
    List<T> findAll();
    void update(T entity);
    void close();
}
