package com.umutakbulut.library.service;

import com.umutakbulut.library.domain.Book;
import com.umutakbulut.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository ;

    public Book save(Book book){

        book.setId(UUID.randomUUID().toString());

        bookRepository.save(book);

        return book;
    }

    public Book find(String id){
        return bookRepository.findBy(id);
    }

    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    public boolean update(Book book){

        if (!isBookExist(book))
            return false;

        bookRepository.save(book);
        return true;
    }

    public Boolean delete(Book book){

        if (!isBookExist(book)) {
            return false;
        }

        bookRepository.delete(book);
        return true;
    }

    public boolean isBookExist(Book book){
        return bookRepository.findBy(book.getId()) != null;
    }

}
