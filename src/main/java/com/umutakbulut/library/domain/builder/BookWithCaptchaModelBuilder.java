package com.umutakbulut.library.domain.builder;

import com.umutakbulut.library.domain.model.BookWithCaptchaModel;

public class BookWithCaptchaModelBuilder {

    private String id;
    private String name;
    private String author;
    private String challangeField;
    private String responseField;

    public BookWithCaptchaModel build() {
        BookWithCaptchaModel bookWithCaptchaModel = new BookWithCaptchaModel();
        bookWithCaptchaModel.setId(id);
        bookWithCaptchaModel.setName(name);
        bookWithCaptchaModel.setAuthor(author);
        bookWithCaptchaModel.setChallangeField(challangeField);
        bookWithCaptchaModel.setResponseField(responseField);
        return bookWithCaptchaModel;
    }

    public BookWithCaptchaModelBuilder id(String id) {
        this.id = id;
        return this;
    }

    public BookWithCaptchaModelBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BookWithCaptchaModelBuilder author(String author) {
        this.author = author;
        return this;
    }

    public BookWithCaptchaModelBuilder responseField(String responseField) {
        this.responseField = responseField;
        return this;
    }

    public BookWithCaptchaModelBuilder challangeField(String challangeField) {
        this.challangeField = challangeField;
        return this;
    }
}
