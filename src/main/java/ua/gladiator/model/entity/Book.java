package ua.gladiator.model.entity;

import java.time.LocalDate;
import java.util.*;

public class Book {
    private Long id;
    private String author;
    private String name;
    private String text;
    private Integer daysToReturn;
    private String picUrl;
    private Boolean isAvailable;
    private LocalDate addDate;
    private List<String> attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getDaysToReturn() {
        return daysToReturn;
    }

    public void setDaysToReturn(Integer daysToReturn) {
        this.daysToReturn = daysToReturn;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", daysToReturn=" + daysToReturn +
                ", picUrl='" + picUrl + '\'' +
                ", isAvailable=" + isAvailable +
                ", addDate=" + addDate +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(author, book.author) &&
                Objects.equals(name, book.name) &&
                Objects.equals(text, book.text) &&
                Objects.equals(daysToReturn, book.daysToReturn) &&
                Objects.equals(picUrl, book.picUrl) &&
                Objects.equals(isAvailable, book.isAvailable) &&
                Objects.equals(addDate, book.addDate) &&
                Objects.equals(attributes, book.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, name, text, daysToReturn, picUrl, isAvailable, addDate, attributes);
    }
}
