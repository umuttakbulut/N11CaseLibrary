package com.umutakbulut.library.domain.builder;

import com.umutakbulut.library.domain.model.BookModel;

public class BookModelBuilder {

    private String id;
    private String name;
    private String author;


    public BookModel build(){
        BookModel bookModel = new BookModel();
        bookModel.setId(id);
        bookModel.setName(name);
        bookModel.setAuthor(author);
        return bookModel;
    }

    public BookModelBuilder id(String id) {
        this.id = id;
        return this;
    }

    public BookModelBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BookModelBuilder author(String author) {
        this.author = author;
        return this;
    }


}
