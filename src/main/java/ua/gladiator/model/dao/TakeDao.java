package ua.gladiator.model.dao;

import ua.gladiator.model.entity.Take;

import java.util.*;

public interface TakeDao extends GenericDao<Take> {
    List<Take> findByParams(Boolean isReturned, String email, Long userId);
}
