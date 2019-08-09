package ua.gladiator.model.service.impl;

import ua.gladiator.model.dao.AttributeDao;
import ua.gladiator.model.dao.BookDao;
import ua.gladiator.model.dao.DaoFactory;
import ua.gladiator.model.dao.TakeDao;
import ua.gladiator.model.entity.Attribute;
import ua.gladiator.model.entity.Book;
import ua.gladiator.model.entity.Take;
import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.builders.TakeBuilder;
import ua.gladiator.model.entity.exception.BookNotFoundException;
import ua.gladiator.model.service.BookService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class BookServiceImpl implements BookService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private TakeDao takeDao;
    private BookDao bookDao;
    private AttributeDao attributeDao;

    private static ResourceBundle rb = ResourceBundle.getBundle("properties.db", new Locale("en", "US"));


    @Override
    public Book addBook(Book book) {
        bookDao = daoFactory.createBookDao();
        bookDao.create(book);
        bookDao.close();
        return book;
    }

    @Override
    public Book takeBook(User user, Long bookId) {
        bookDao = daoFactory.createBookDao();
        takeDao = daoFactory.createTakeDao();

        Book book = bookDao.findById(bookId).orElseThrow(() -> new BookNotFoundException("No books with id=" + bookId));

        Take take = TakeBuilder
                .builder()
                .buildBookName(book.getName())
                .buildBookId(book.getId())
                .buildBookAuthor(book.getAuthor())
                .buildReturnDeadline(LocalDate.now().plusDays(book.getDaysToReturn()))
                .buildTakeDate(LocalDate.now())
                .buildBookPicUrl(book.getPicUrl())
                .buildIsReturned(false)
                .buildUserEmail(user.getEmail())
                .buildUserId(user.getId())
                .buildUserPhone(user.getPhoneNumber())
                .build();

        book.setAvailable(false);

        bookDao.update(book);
        takeDao.create(take);

        takeDao.close();
        bookDao.close();
        return book;
    }


    @Override
    public List<Book> getBooksByParams(List<String> attributes, String line, String author, Integer page) {
        bookDao = daoFactory.createBookDao();
        attributeDao = daoFactory.createAttributeDao();

        Integer pageSize = Integer.parseInt(rb.getString("page.size.books"));
        if (page == null) {
            page = 1;
        }
        Integer startingElement = (page - 1) * pageSize;

        List<Book> bookList;
        if (attributes.size() == 0) {
            attributeDao.findAll().forEach(v -> attributes.add(v.getName()));
        }
        bookList = attributes
                .stream()
                .map(v ->  bookDao.findByParams(v, author, line, startingElement, pageSize))
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());


        bookDao.close();
        return bookList;
    }
}
