/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pluralsight;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import jersey.repackaged.com.google.common.util.concurrent.ListeningExecutorService;

/**
 *
 * @author Emm
 */
public class BookDAO {

    private Map<String, Book> books;
    private ListeningExecutorService service;

    public BookDAO() {

        books = new ConcurrentHashMap<String, Book>();
        service = (ListeningExecutorService) MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        /*
        this.books = new HashMap<String, Book>();

        Book book1 = new Book();
        book1.setId("1");
        book1.setTitle("xxxxxxx");
        book1.setAuthor("xxxxxx");
        book1.setPublished(new Date());
        book1.setIsbn("xxxxxxxx");

        Book book2 = new Book();
        book2.setId("2");
        book2.setTitle("aaaaaaaaaaaa");
        book2.setAuthor("aaaaaaaaa");
        book2.setPublished(new Date());
        book2.setIsbn("aaaaaaaaaaa");

        books.put(book1.getId(), book1);
        books.put(book2.getId(), book2);
         */
    }

    Book addBook(Book book) {

        book.setId(UUID.randomUUID().toString());
        books.put(book.getId(), book);
        return book;
    }

    List<Book> getBooks() {
        List<Book> list = new ArrayList<Book>(books.values());
        return list;
    }

    Book getBook(String id) {
        return (books.get(id));
    }

    ListenableFuture<Book> addBookAsync(final Book book) {

        ListenableFuture<Book> future = (ListenableFuture<Book>) service.submit(new Callable<Book>() {
            public Book call() throws Exception {
                return addBook(book);
            }
        });

        return future;

    }

}
