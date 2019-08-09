package ua.gladiator.model.entity;

import java.time.LocalDate;
import java.util.*;

public class Take {
    private Long id;
    private String bookName;
    private String userEmail;
    private Integer userPhone;
    private String bookAuthor;
    private String bookPicUrl;
    private LocalDate takeDate;
    private LocalDate returnDate;
    private LocalDate returnDeadline;
    private Long bookId;
    private Long userId;
    private Boolean isReturned;

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    @Override
    public String toString() {
        return "Take{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone=" + userPhone +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPicUrl='" + bookPicUrl + '\'' +
                ", takeDate=" + takeDate +
                ", returnDate=" + returnDate +
                ", returnDeadline=" + returnDeadline +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", isReturned=" + isReturned +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Take take = (Take) o;
        return Objects.equals(id, take.id) &&
                Objects.equals(bookName, take.bookName) &&
                Objects.equals(userEmail, take.userEmail) &&
                Objects.equals(userPhone, take.userPhone) &&
                Objects.equals(bookAuthor, take.bookAuthor) &&
                Objects.equals(bookPicUrl, take.bookPicUrl) &&
                Objects.equals(takeDate, take.takeDate) &&
                Objects.equals(returnDate, take.returnDate) &&
                Objects.equals(returnDeadline, take.returnDeadline) &&
                Objects.equals(bookId, take.bookId) &&
                Objects.equals(userId, take.userId) &&
                Objects.equals(isReturned, take.isReturned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, userEmail, userPhone, bookAuthor, bookPicUrl, takeDate, returnDate, returnDeadline, bookId, userId, isReturned);
    }
}

