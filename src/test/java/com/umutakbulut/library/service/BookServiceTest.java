package com.umutakbulut.library.service;

import com.umutakbulut.library.domain.Book;
import com.umutakbulut.library.domain.builder.BookBuilder;
import com.umutakbulut.library.repository.BookRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void shouldSaveBook() {
        Book book = new BookBuilder().author("author").name("name").build();

        Book savedBook = service.save(book);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).save(bookCaptor.capture());

        Book capturedBook = bookCaptor.getValue();

        assertThat(savedBook, equalTo(capturedBook));
        assertThat(savedBook.getAuthor(), equalTo("author"));
        assertThat(savedBook.getName(), equalTo("name"));
    }

    @Test
    public void shouldFindBook() {
        Book book = new BookBuilder().build();

        when(bookRepository.findBy("id")).thenReturn(book);

        Book findingBook = service.find("id");

        assertThat(findingBook, equalTo(book));
    }

    @Test
    public void shouldListAllBook() {
        Book book = new BookBuilder().build();

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        List<Book> results = service.listAll();

        assertThat(results.size(), equalTo(1));
        assertThat(results, hasItem(book));
    }

    @Test
    public void shouldUpdateBook() {
        Book book = new BookBuilder().id("id").author("author").name("name").build();

        when(bookRepository.findBy("id")).thenReturn(book);

        boolean result = service.update(book);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).save(bookCaptor.capture());

        Book capturedBook = bookCaptor.getValue();

        assertions(result, capturedBook);
    }

    @Test
    public void shouldNotUpdateBookWhenBookNotExist() {
        Book book = new BookBuilder().id("id").author("author").name("name").build();

        when(bookRepository.findBy("id")).thenReturn(null);

        boolean result = service.update(book);

        verify(bookRepository,never()).save(any(Book.class));

        assertThat(result, equalTo(false));
    }

    @Test
    public void shouldDeleteBook() {
        Book book = new BookBuilder().id("id").author("author").name("name").build();

        when(bookRepository.findBy("id")).thenReturn(book);

        boolean result = service.delete(book);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).delete(bookCaptor.capture());

        Book capturedBook = bookCaptor.getValue();

        assertions(result, capturedBook);
    }

    @Test
    public void shouldNotDeleteBookWhenBookNotExist() {
        Book book = new BookBuilder().id("id").author("author").name("name").build();

        when(bookRepository.findBy("id")).thenReturn(null);

        boolean result = service.delete(book);

        verify(bookRepository,never()).delete(any(Book.class));

        assertThat(result, equalTo(false));
    }

    private void assertions(boolean result, Book book) {
        assertThat(result, equalTo(true));
        assertThat(book.getId(), equalTo("id"));
        assertThat(book.getAuthor(), equalTo("author"));
        assertThat(book.getName(), equalTo("name"));
    }

}

