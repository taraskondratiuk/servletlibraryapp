package main.java.ua.gladiator.model.entity.builders;

import main.java.ua.gladiator.model.entity.Take;

import java.time.LocalDate;

public final class TakeBuilder {
    private Long id;
    private String bookName;
    private String userEmail;
    private Integer userPhone;
    private String bookAuthor;
    private String bookPicUrl;
    private LocalDate takeDate;
    private LocalDate returnDate;
    private LocalDate returnDeadline;

    private TakeBuilder() {
    }

    public static TakeBuilder builder() {
        return new TakeBuilder();
    }

    public TakeBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public TakeBuilder buildBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public TakeBuilder buildUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public TakeBuilder buildUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public TakeBuilder buildBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
        return this;
    }

    public TakeBuilder buildBookPicUrl(String bookPicUrl) {
        this.bookPicUrl = bookPicUrl;
        return this;
    }

    public TakeBuilder buildTakeDate(LocalDate takeDate) {
        this.takeDate = takeDate;
        return this;
    }

    public TakeBuilder buildReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public TakeBuilder buildReturnDeadline(LocalDate returnDeadline) {
        this.returnDeadline = returnDeadline;
        return this;
    }

    public Take build() {
        Take take = new Take();
        take.setId(id);
        take.setBookName(bookName);
        take.setUserEmail(userEmail);
        take.setUserPhone(userPhone);
        take.setBookAuthor(bookAuthor);
        take.setBookPicUrl(bookPicUrl);
        take.setTakeDate(takeDate);
        take.setReturnDate(returnDate);
        take.setReturnDeadline(returnDeadline);
        return take;
    }
}
