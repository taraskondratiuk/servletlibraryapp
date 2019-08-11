package ua.gladiator.model.service;

import ua.gladiator.model.entity.Book;
import ua.gladiator.model.entity.Take;
import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.dto.Page;

import java.util.*;

public interface TakeService {
    Take makeTakeReturned(Long id);

    Page<Take> getFilteredTakes(String isReturnedString, Long id, String email, Integer pageNum);
}
