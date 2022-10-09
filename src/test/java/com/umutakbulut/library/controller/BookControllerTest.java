package com.umutakbulut.library.controller;

import com.umutakbulut.library.domain.Book;
import com.umutakbulut.library.domain.builder.BookBuilder;
import com.umutakbulut.library.domain.builder.BookModelBuilder;
import com.umutakbulut.library.domain.builder.CaptchaModelBuilder;
import com.umutakbulut.library.domain.model.BookModel;
import com.umutakbulut.library.domain.model.BookWithCaptchaModel;
import com.umutakbulut.library.service.BookService;
import com.umutakbulut.library.service.CaptchaService;
import com.umutakbulut.library.domain.model.CaptchaModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletRequest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @InjectMocks
    private BookController controller;

    @Mock
    private BookService bookService;

    @Mock
    private CaptchaService captchaService;

    @Mock
    private ServletRequest request;

    @Test
    public void shouldReturnHomePage(){
        assertThat(controller.getBookPage(), equalTo("books"));
    }

    @Test
    public void shouldNotSaveBookWhenCaptchaIsWrong() {
        BookWithCaptchaModel bookWithCaptchaModel = mock(BookWithCaptchaModel.class);
        CaptchaModel captchaModel = new CaptchaModel();

        when(bookWithCaptchaModel.createCaptchaModel()).thenReturn(captchaModel);
        when(captchaService.captchaIsNotValid(captchaModel)).thenReturn(true);

        String result = controller.save(bookWithCaptchaModel, request);

        verify(bookService, never()).save(any(Book.class));

        assertThat(result, equalTo("wrongCaptcha"));
    }

    @Test
    public void shouldSaveBookWhenCaptchaIsCorrect() {
        BookWithCaptchaModel bookWithCaptchaModel = mock(BookWithCaptchaModel.class);
        BookModel bookModel = new BookModelBuilder().author("author").name("name").build();
        CaptchaModel captchaModel = new CaptchaModelBuilder().answer("answer").imageWord("imageWord").build();

        when(bookWithCaptchaModel.createCaptchaModel()).thenReturn(captchaModel);
        when(bookWithCaptchaModel.createBookModel()).thenReturn(bookModel);
        when(request.getRemoteAddr()).thenReturn("ipAddress");
        when(captchaService.captchaIsNotValid(captchaModel)).thenReturn(false);

        String result = controller.save(bookWithCaptchaModel, request);

        ArgumentCaptor<CaptchaModel> captchaModelCaptor = ArgumentCaptor.forClass(CaptchaModel.class);
        verify(captchaService).captchaIsNotValid(captchaModelCaptor.capture());
        verify(bookService).save(any(Book.class));

        assertThat(result, equalTo("successSave"));
        assertThat(captchaModel.getAnswer(), equalTo("answer"));
        assertThat(captchaModel.getImageWord(), equalTo("imageWord"));
        assertThat(captchaModel.getIpAddress(), equalTo("ipAddress"));
    }

    @Test
    public void shouldSaveBook() {
        BookWithCaptchaModel bookWithCaptchaModel = mock(BookWithCaptchaModel.class);
        BookModel bookModel = mock(BookModel.class);
        Book book = new BookBuilder().author("author").name("name").build();
        CaptchaModel captchaModel = new CaptchaModelBuilder().build();

        when(bookWithCaptchaModel.createCaptchaModel()).thenReturn(captchaModel);
        when(bookWithCaptchaModel.createBookModel()).thenReturn(bookModel);
        when(bookModel.createBook()).thenReturn(book);
        when(bookModel.isNotValid()).thenReturn(false);
        when(captchaService.captchaIsNotValid(captchaModel)).thenReturn(false);

        String result = controller.save(bookWithCaptchaModel, request);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

        verify(bookService).save(bookCaptor.capture());

        Book capturedBook = bookCaptor.getValue();
        assertThat(result, equalTo("successSave"));
        assertThat(capturedBook.getAuthor(), equalTo("author"));
        assertThat(capturedBook.getName(), equalTo("name"));
    }

    @Test
    public void shouldNotSaveBook() {
        BookWithCaptchaModel bookWithCaptchaModel = mock(BookWithCaptchaModel.class);
        BookModel bookModel = mock(BookModel.class);
        Book book = new BookBuilder().author("author").name("name").build();
        CaptchaModel captchaModel = new CaptchaModelBuilder().build();

        when(bookWithCaptchaModel.createCaptchaModel()).thenReturn(captchaModel);
        when(bookWithCaptchaModel.createBookModel()).thenReturn(bookModel);
        when(bookModel.createBook()).thenReturn(book);
        when(bookModel.isNotValid()).thenReturn(true);
        when(captchaService.captchaIsNotValid(captchaModel)).thenReturn(false);

        String result = controller.save(bookWithCaptchaModel, request);

        assertThat(result, equalTo("failSave"));
        verify(bookService, never()).save(any(Book.class));
    }

    @Test
    public void shouldUpdateBook() {
        BookWithCaptchaModel bookWithCaptchaModel = mock(BookWithCaptchaModel.class);
        BookModel bookModel = mock(BookModel.class);
        Book book = new BookBuilder().id("id").author("author").name("name").build();

        when(bookWithCaptchaModel.createBookModel()).thenReturn(bookModel);
        when(bookModel.createBook()).thenReturn(book);
        when(bookModel.isNotValid()).thenReturn(false);
        when(bookService.update(any(Book.class))).thenReturn(true);

        boolean result = controller.update(bookModel);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

        verify(bookService).update(bookCaptor.capture());

        Book capturedBook = bookCaptor.getValue();

        assertions(result, capturedBook);
    }

    @Test
    public void shouldNotUpdateBook() {
        BookWithCaptchaModel bookWithCaptchaModel = mock(BookWithCaptchaModel.class);
        BookModel bookModel = mock(BookModel.class);
        Book book = new BookBuilder().id("id").author("author").name("name").build();

        when(bookWithCaptchaModel.createBookModel()).thenReturn(bookModel);
        when(bookModel.createBook()).thenReturn(book);
        when(bookModel.isNotValid()).thenReturn(true);
        when(bookService.update(any(Book.class))).thenReturn(true);

        boolean result = controller.update(bookModel);

        verify(bookService, never()).save(any(Book.class));

        assertThat(result, equalTo(false));
    }

    @Test
    public void shouldDeleteBook(){
        BookModel bookModel = mock(BookModel.class);

        Book book = new BookBuilder().id("id").name("name").author("author").build();

        when(bookModel.createBook()).thenReturn(book);
        when(bookService.delete(book)).thenReturn(true);

        boolean result = controller.delete(bookModel);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);

        verify(bookService).delete(bookCaptor.capture());

        Book capturedBook = bookCaptor.getValue();

        assertions(result, capturedBook);
    }

    @Test
    public void shouldFindBook(){
        BookModel bookModel = new BookModelBuilder().id("id").build();
        Book book = new Book();

        when(bookService.find("id")).thenReturn(book);

        Book findingBook = controller.find(bookModel);

        assertThat(findingBook, equalTo(book));
    }

    @Test
    public void shouldGetAllBook(){
        Book book = new Book();

        when(bookService.listAll()).thenReturn(Arrays.asList(book));

        List<Book> results = controller.getAll();

        assertThat(results.size(), equalTo(1));
        assertThat(results, hasItem(book));
    }

    private void assertions(boolean result, Book capturedBook) {
        assertThat(result, equalTo(true));
        assertThat(capturedBook.getAuthor(), equalTo("author"));
        assertThat(capturedBook.getName(), equalTo("name"));
        assertThat(capturedBook.getId(), equalTo("id"));
    }

}
