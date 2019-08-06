package main.java.ua.gladiator.model.entity;

import java.time.LocalDate;

public class Take {
    private Long id;

    private String bookName;

    private String userEmail;

    private Integer userPhone;

    private String bookAuthor;

    private String bookPicUrl;

    private String bookText;

    private LocalDate takeDate;

    private LocalDate returnDate;

    private LocalDate returnDeadline;

    private Boolean isReturned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPicUrl() {
        return bookPicUrl;
    }

    public void setBookPicUrl(String bookPicUrl) {
        this.bookPicUrl = bookPicUrl;
    }

    public String getBookText() {
        return bookText;
    }

    public void setBookText(String bookText) {
        this.bookText = bookText;
    }

    public LocalDate getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(LocalDate takeDate) {
        this.takeDate = takeDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDeadline() {
        return returnDeadline;
    }

    public void setReturnDeadline(LocalDate returnDeadline) {
        this.returnDeadline = returnDeadline;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }
}
