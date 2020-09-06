
package com.ajeet.spark.app.repo.impl;

import com.ajeet.spark.app.model.Book;
import com.ajeet.spark.app.repo.ifc.IBookRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BookRepository implements IBookRepository {
    private static Map<Integer, Book> books = new HashMap<Integer, Book>();

    private AtomicInteger counter = new AtomicInteger();

    @Override
    public Collection<Book> getAll(){
        return books.values();
    }

    @Override
    public Book get(int id){
        return books.get(id);
    }

    @Override
    public int put(Book book){
        int id = counter.incrementAndGet();
        books.put(id,book);
        return id;
    }

    @Override
    public void remove(int id){
        books.remove(id);
    }
}
