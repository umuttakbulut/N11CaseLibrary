package com.umutakbulut.library.domain.model;

import com.umutakbulut.library.domain.Book;
import org.apache.commons.lang.StringUtils;

public class BookModel {

    private String id;
    private String name;
    private String author;

    public Book createBook() {
        Book book = new Book();
        book.setAuthor(this.getAuthor());
        book.setName(this.getName());
        book.setId(this.getId());
        return book;
    }

    public boolean isValid() {
        if(StringUtils.isEmpty(this.author) || StringUtils.isEmpty(this.name)) {
            return false;
        }
        return true;
    }

    public boolean isNotValid() {
        return !isValid();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
