
package com.ajeet.spark.app.svc.impl;

import com.ajeet.spark.app.model.Book;
import com.ajeet.spark.app.repo.ifc.IBookRepository;
import com.ajeet.spark.app.repo.impl.BookRepository;
import com.ajeet.spark.app.svc.ifc.IBookResponse;
import com.ajeet.spark.app.svc.ifc.IBooksApi;
import com.ajeet.spark.app.util.JsonTransformer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BooksApi implements IBooksApi {

    private IBookRepository repository = new BookRepository();
    private JsonTransformer transformer = new JsonTransformer();

    @Override
    public IBookResponse add() {
        return (request, response) -> {
            Book book = transformer.render(Book.class, request.body());
            int id = repository.put(book);
            response.status(200);
            response.header("Content-Type", "application/json");
            return getResponseMap(String.valueOf(id), "success");
        };
    }

    @Override
    public IBookResponse get() {
        return (request, response) -> {
            String id = request.params(":id");
            Book book = repository.get(Integer.parseInt(id));
            response.status(200);
            response.header("Content-Type", "application/json");
            return book;
        };
    }

    @Override
    public IBookResponse getAll() {
        return (request, response) -> {
            Collection<Book> books = repository.getAll();
            response.status(200);
            response.header("Content-Type", "application/json");
            return books;
        };
    }

    @Override
    public IBookResponse delete() {
        return (request, response) -> {
            String id = request.params(":id");
            repository.remove(Integer.parseInt(id));
            response.status(200);
            response.header("Content-Type", "application/json");
            return getResponseMap(String.valueOf(id), "success");
        };
    }

    private Map<String, String> getResponseMap(String id, String status) {
        Map<String, String> data = new HashMap<>();
        data.put("Id", id);
        data.put("status", status);
        return data;
    }
}
