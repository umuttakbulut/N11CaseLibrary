package com.umutakbulut.library.domain.model;

public class BookWithCaptchaModel {

    private String name;
    private String author;
    private String responseField;
    private String challangeField;
    private String id;

    public BookModel createBookModel(){
        BookModel bookModel = new BookModel();
        bookModel.setAuthor(this.getAuthor());
        bookModel.setName(this.getName());
        return bookModel;
    }

    public CaptchaModel createCaptchaModel(){
        CaptchaModel captchaModel = new CaptchaModel();
        captchaModel.setAnswer(this.responseField);
        captchaModel.setImageWord(this.challangeField);
        return captchaModel;
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

    public String getResponseField() {
        return responseField;
    }

    public void setResponseField(String responseField) {
        this.responseField = responseField;
    }

    public String getChallangeField() {
        return challangeField;
    }

    public void setChallangeField(String challangeField) {
        this.challangeField = challangeField;
    }
}
