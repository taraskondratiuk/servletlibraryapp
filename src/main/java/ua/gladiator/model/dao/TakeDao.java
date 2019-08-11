package ua.gladiator.model.dao;

import ua.gladiator.model.entity.Take;

import java.util.*;

public interface TakeDao extends GenericDao<Take> {
    List<Take> findByParams(String isReturned, String email, Long userId, Integer startingEl, Integer pageSize);

    Integer countByParams(String isReturned, String email, Long userId);
}
