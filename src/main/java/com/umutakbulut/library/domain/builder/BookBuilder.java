package com.umutakbulut.library.domain.builder;

import com.umutakbulut.library.domain.Book;

public class BookBuilder {

    private String id;
    private String name;
    private String author;

    public Book build() {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        return book;
    }

    public BookBuilder id(String id) {
        this.id = id;
        return this;
    }

    public BookBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder author(String author) {
        this.author = author;
        return this;
    }
}
