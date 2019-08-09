package ua.gladiator.model.service;

import ua.gladiator.model.entity.Book;
import ua.gladiator.model.entity.Take;
import ua.gladiator.model.entity.User;

import java.util.*;

public interface TakeService {
    Take makeTakeReturned(User user, Long id);

    List<Take> getFilteredTakes(Boolean isReturned, Long id, String email, Integer page);
}
