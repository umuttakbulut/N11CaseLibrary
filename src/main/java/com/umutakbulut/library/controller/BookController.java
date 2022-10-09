package com.umutakbulut.library.controller;

import com.umutakbulut.library.domain.Book;
import com.umutakbulut.library.domain.model.BookModel;
import com.umutakbulut.library.domain.model.BookWithCaptchaModel;
import com.umutakbulut.library.domain.model.CaptchaModel;
import com.umutakbulut.library.service.BookService;

import com.umutakbulut.library.service.CaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CaptchaService captchaService;

    private String HOME_PAGE = "books";

    @RequestMapping
    public String getBookPage(){
        return HOME_PAGE;
    }

    @RequestMapping(value="/gelAll")
    public @ResponseBody List<Book> getAll() {
        return bookService.listAll();
    }

    @RequestMapping(value="/find")
    public @ResponseBody Book find(@RequestBody BookModel bookModel){
        return bookService.find(bookModel.getId());
    }

    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody String save(@RequestBody BookWithCaptchaModel bookWithCaptchaModel, ServletRequest request){
        CaptchaModel captchaModel = bookWithCaptchaModel.createCaptchaModel();
        captchaModel.setIpAddress(request.getRemoteAddr());
        if(captchaService.captchaIsNotValid(captchaModel)) {
            return "Fail Captcha";
        }

        BookModel bookModel = bookWithCaptchaModel.createBookModel();
        if(bookModel.isNotValid()) {
            return "Fail Save Operation";
        }

        bookService.save(bookModel.createBook());

        return "Success Save Operation";
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public @ResponseBody boolean update(@RequestBody BookModel bookModel){
        if(bookModel.isNotValid()) {
            return false;
        }
        return bookService.update(bookModel.createBook());
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody boolean delete(@RequestBody BookModel bookModel){
         return bookService.delete(bookModel.createBook());
    }
}