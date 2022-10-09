package com.umutakbulut.library.domain.model;

import com.umutakbulut.library.domain.Book;
import com.umutakbulut.library.domain.builder.BookModelBuilder;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BookModelTest {

    @Test
    public void shouldReturnFalseWhenAuthorNotExistInModel() {
        BookModel bookModel = new BookModelBuilder().author(null).name("name").build();

        boolean result = bookModel.isValid();

        assertThat(result, equalTo(false));
    }

    @Test
    public void shouldReturnFalseWhenNameNotExistInModel() {
        BookModel bookModel = new BookModelBuilder().author("author").name(null).build();

        boolean result = bookModel.isValid();

        assertThat(result, equalTo(false));
    }

    @Test
    public void shouldReturnTrueWhenModelValid() {
        BookModel bookModel = new BookModelBuilder().author("author").name("name").build();

        boolean result = bookModel.isValid();

        assertThat(result, equalTo(true));
    }

    @Test
    public void shouldCreateBook(){
        BookModel bookModel = new BookModelBuilder().id("id").name("name").author("author").build();

        Book book = bookModel.createBook();

        assertThat(book.getId(),equalTo("id"));
        assertThat(book.getName(),equalTo("name"));
        assertThat(book.getAuthor(),equalTo("author"));
    }
}
