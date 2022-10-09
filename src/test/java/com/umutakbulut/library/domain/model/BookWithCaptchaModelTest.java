package com.umutakbulut.library.domain.model;


import com.umutakbulut.library.domain.builder.BookWithCaptchaModelBuilder;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BookWithCaptchaModelTest {

    @Test
    public void shouldCreateBookModel() {
        BookWithCaptchaModel bookWithCaptchaModel = new BookWithCaptchaModelBuilder().name("name").author("author").build();

        BookModel bookModel = bookWithCaptchaModel.createBookModel();

        assertThat(bookModel.getId(),equalTo(null));
        assertThat(bookModel.getAuthor(),equalTo("author"));
        assertThat(bookModel.getName(),equalTo("name"));
    }

    @Test
    public void shouldCreateCaptchaModel() {
        BookWithCaptchaModel bookWithCaptchaModel = new BookWithCaptchaModelBuilder().challangeField("chalangeField").responseField("responseField").build();

        CaptchaModel captchaModel = bookWithCaptchaModel.createCaptchaModel();

        assertThat(captchaModel.getIpAddress(),equalTo(null));
        assertThat(captchaModel.getImageWord(),equalTo("chalangeField"));
        assertThat(captchaModel.getAnswer(),equalTo("responseField"));
    }
}
