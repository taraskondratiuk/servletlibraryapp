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
import ua.gladiator.model.entity.dto.Page;
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
    public Boolean isNameUnique(String name) {
        bookDao = daoFactory.createBookDao();
        Boolean isUnique = bookDao.isUnique(name);
        bookDao.close();
        return isUnique;
    }

    @Override
    public Book addBook(Book book) {
        bookDao = daoFactory.createBookDao();
        bookDao.create(book);
        book.getAttributes().forEach(v -> bookDao.addAttribute(book.getName(), v));

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

        bookDao.setUnavailable(bookId);
        takeDao.create(take);

        takeDao.close();
        bookDao.close();
        return book;
    }

    @Override
    public Page<Book> getBooksByParams(String attributes[], String line, String author, Integer pageNum) {
        bookDao = daoFactory.createBookDao();
        attributeDao = daoFactory.createAttributeDao();


        Integer pageSize = Integer.parseInt(rb.getString("page.size.books"));

        Integer startingElement = (pageNum - 1) * pageSize;

        List<Book> bookList;
        List<String> attributesList = Arrays.stream(attributes).collect(Collectors.toList());
        if (attributes[0].equals("")) {
            attributeDao.findAll().forEach(v -> attributesList.add(v.getName()));
        }
        bookList = bookDao.findByParams(attributesList, author, line, startingElement, pageSize);

        Integer count = bookDao.countByParams(attributesList, author, line);
        Integer totalPages = (count / pageSize) + Integer.signum(count % pageSize);

        Page<Book> bookPage = new Page<>(bookList, totalPages, pageSize, pageNum);
        attributeDao.close();
        bookDao.close();
        return bookPage;
    }

    @Override
    public void deleteBook(Long id) {
        bookDao = daoFactory.createBookDao();

        bookDao.deleteBooksAttributes(id);
        bookDao.delete(id);

        bookDao.close();
    }
}
