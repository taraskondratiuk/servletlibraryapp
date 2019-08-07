package main.java.ua.gladiator.model.entity.builders;

import main.java.ua.gladiator.model.entity.Book;

import java.time.LocalDate;
import java.util.*;

public final class BookBuilder {
    private Long id;
    private String author;
    private String name;
    private String text;
    private Integer daysToReturn;
    private String picUrl;
    private LocalDate addDate;
    private List<String> attributes;

    private BookBuilder() {
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public BookBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public BookBuilder buildAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder buildText(String text) {
        this.text = text;
        return this;
    }

    public BookBuilder buildDaysToReturn(Integer daysToReturn) {
        this.daysToReturn = daysToReturn;
        return this;
    }

    public BookBuilder buildPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public BookBuilder buildAddDate(LocalDate addDate) {
        this.addDate = addDate;
        return this;
    }

    public BookBuilder buildAttributes(List<String> attributes) {
        this.attributes = attributes;
        return this;
    }

    public BookBuilder buildAttributesWithString(String attributes) {
        this.attributes = Arrays.asList(attributes.split(",", 0));
        return this;
    }

    public Book build() {
        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setName(name);
        book.setText(text);
        book.setDaysToReturn(daysToReturn);
        book.setPicUrl(picUrl);
        book.setAddDate(addDate);
        book.setAttributes(attributes);
        return book;
    }
}
