package ua.gladiator.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.gladiator.model.dao.TakeDao;
import ua.gladiator.model.dao.impl.JDBCTakeDao;
import ua.gladiator.model.entity.Take;
import ua.gladiator.model.entity.builders.TakeBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TakeDaoTest {

    @Mock
    private DataSource ds;

    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;

    private Take t;

    @Before
    public void setUp() throws Exception {
        assertNotNull(ds);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getConnection()).thenReturn(c);
        t = new Take();
        t.setId(1L);
        t.setBookAuthor("kipling");
        t.setBookName("treasure island");
        when(rs.first()).thenReturn(true);
        when(rs.getLong(1)).thenReturn(1L);
        when(rs.getString(2)).thenReturn(t.getBookAuthor());
        when(rs.getString(3)).thenReturn(t.getBookName());
        when(stmt.executeQuery()).thenReturn(rs);

    }

    @Test(expected=IllegalArgumentException.class)
    public void nullCreateThrowsException() {
        new JDBCTakeDao(c).create(null);
    }

    @Test
    public void saveTake() {

        Take take = TakeBuilder
                .builder()
                .buildBookAuthor("lasdjf")
                .buildBookId(228L)
                .buildBookName("sadjkl")
                .buildReturnDeadline(LocalDate.now())
                .build();
        JDBCTakeDao takeDao = new JDBCTakeDao(c);
        takeDao.create(take);
    }
}
