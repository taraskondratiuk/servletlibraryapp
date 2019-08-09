package ua.gladiator.model.service.impl;

import ua.gladiator.model.dao.AttributeDao;
import ua.gladiator.model.dao.BookDao;
import ua.gladiator.model.dao.DaoFactory;
import ua.gladiator.model.dao.TakeDao;
import ua.gladiator.model.entity.Book;
import ua.gladiator.model.entity.Take;
import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.builders.TakeBuilder;
import ua.gladiator.model.entity.exception.BookNotFoundException;
import ua.gladiator.model.service.TakeService;
import ua.gladiator.model.entity.exception.TakeNotFoundException;

import java.time.LocalDate;
import java.util.*;

public class TakeServiceImpl implements TakeService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private TakeDao takeDao;
    private BookDao bookDao;

    private static ResourceBundle rb = ResourceBundle.getBundle("properties.db", new Locale("en", "US"));

    @Override
    public Take makeTakeReturned(User user, Long id) {
        bookDao = daoFactory.createBookDao();
        takeDao = daoFactory.createTakeDao();
        Take take = takeDao.findById(id).orElseThrow(() -> new TakeNotFoundException("No takes with id=" + id));

        take.setReturned(true);
        take.setReturnDate(LocalDate.now());
        Book book = bookDao.findById(take.getBookId()).orElseThrow(() -> new BookNotFoundException("No books with id=" + id));
        book.setAvailable(true);
        bookDao.update(book);
        takeDao.update(take);

        takeDao.close();
        bookDao.close();
        return take;
    }


    @Override
    public List<Take> getFilteredTakes(Boolean isReturned, Long id, String email, Integer page) {
        takeDao = daoFactory.createTakeDao();


        Integer pageSize = Integer.parseInt(rb.getString("page.size.takes"));


        if (page == null) {
            page = 1;
        }
        Integer startingElement = (page - 1) * pageSize;

        List<Take> takes = takeDao.findByParams(isReturned, email, id, startingElement, pageSize);
        takeDao.close();
        return takes;
    }
}
