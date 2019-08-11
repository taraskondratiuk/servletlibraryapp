package ua.gladiator.model.service.impl;

import ua.gladiator.model.dao.BookDao;
import ua.gladiator.model.dao.DaoFactory;
import ua.gladiator.model.dao.TakeDao;
import ua.gladiator.model.entity.Book;
import ua.gladiator.model.entity.Take;
import ua.gladiator.model.entity.User;
import ua.gladiator.model.entity.dto.Page;
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
    public Take makeTakeReturned(Long id) {
        bookDao = daoFactory.createBookDao();
        takeDao = daoFactory.createTakeDao();
        Take take = takeDao.findById(id).orElseThrow(() -> new TakeNotFoundException("No takes with id=" + id));

        take.setIsReturned(true);
        take.setReturnDate(LocalDate.now());
        bookDao.setAvailable(take.getBookId());
        takeDao.update(take);

        takeDao.close();
        bookDao.close();
        return take;
    }


    @Override
    public Page<Take> getFilteredTakes(String isReturnedString, Long id, String email, Integer pageNum) {
        takeDao = daoFactory.createTakeDao();


        Integer pageSize = Integer.parseInt(rb.getString("page.size.takes"));


        if (pageNum == null) {
            pageNum = 1;
        }
        Integer startingElement = (pageNum - 1) * pageSize;

        List<Take> takes = takeDao.findByParams(isReturnedString, email, id, startingElement, pageSize);

        Integer count = takeDao.countByParams(isReturnedString, email, id);

        Integer totalPages = (count / pageSize) + Integer.signum(count % pageSize);


        takeDao.close();
        return new Page<>(takes, totalPages, pageSize, pageNum);
    }
}
