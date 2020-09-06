package com.ajeet.spark.app.repo.ifc;

import com.ajeet.spark.app.model.Book;

import java.util.Collection;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface IBookRepository {
    Collection<Book> getAll();

    Book get(int id);

    int put(Book book);

    void remove(int id);
}
