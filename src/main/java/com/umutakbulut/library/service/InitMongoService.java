package com.umutakbulut.library.service;

import com.umutakbulut.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.UUID;

public class InitMongoService {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public void init() {
     // Drop existing collections
     // mongoTemplate.dropCollection("book");

        if(mongoTemplate.getCollection("book").getCount() == 0){

            ArrayList<Book> bookArrayList = new ArrayList<Book>();

            Book book = new Book();
            book.setId(UUID.randomUUID().toString());
            book.setAuthor("Ayşe Kulin");
            book.setName("Handan");
            bookArrayList.add(book);

            book = new Book();
            book.setId(UUID.randomUUID().toString());
            book.setAuthor("Paulo Coelho");
            book.setName("Aldatmak");
            bookArrayList.add(book);

            book = new Book();
            book.setId(UUID.randomUUID().toString());
            book.setAuthor("Elif Şafak");
            book.setName("Sakız Sardunya");
            bookArrayList.add(book);

            book = new Book();
            book.setId(UUID.randomUUID().toString());
            book.setAuthor("Sabahattin Ali");
            book.setName("Kürk Mantolu Madonna");
            bookArrayList.add(book);

            book = new Book();
            book.setId(UUID.randomUUID().toString());
            book.setAuthor("Kahraman Tazeoğlu");
            book.setName("Yaralı");
            bookArrayList.add(book);

            book = new Book();
            book.setId(UUID.randomUUID().toString());
            book.setAuthor("Shemi Zargin");
            book.setName("Çocukluğumun Mavi Düşleri");
            bookArrayList.add(book);

            book = new Book();
            book.setId(UUID.randomUUID().toString());
            book.setAuthor("Enver Aysever");
            book.setName("Bu Roman O Kız Okusun Diye Yazıldı");
            bookArrayList.add(book);

            for (Book getBook : bookArrayList) {
                mongoTemplate.insert(getBook, "book");
            }

        }
	}
}
