package ua.gladiator.model.service;

import ua.gladiator.model.entity.Book;
import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.dto.Page;

import java.util.*;

public interface BookService {
    Boolean isNameUnique(String name);

    Book addBook(Book book);
    Book takeBook(User user, Long id);
    Page<Book> getBooksByParams(String attributes[], String line, String author, Integer pageNum);

    void deleteBook(Long id);
}
