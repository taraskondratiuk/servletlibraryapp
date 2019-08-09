package ua.gladiator.model.service;

import ua.gladiator.model.entity.Book;
import ua.gladiator.model.entity.User;

import java.util.*;

public interface BookService {
    Book addBook(Book book);
    Book takeBook(User user, Long id);
    List<Book> getBooksByParams(List<String> attributes, String line, String author, Integer pageNum);
}
