
package com.ajeet.spark.app;

import com.ajeet.spark.app.svc.ifc.IBooksApi;
import com.ajeet.spark.app.svc.impl.BooksApi;
import com.ajeet.spark.app.repo.ifc.IBookRepository;
import com.ajeet.spark.app.repo.impl.BookRepository;
import com.ajeet.spark.app.util.JsonTransformer;

import org.eclipse.jetty.http.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.servlet.SparkApplication;

import static spark.Spark.*;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class Application implements SparkApplication {

    private Logger log = LoggerFactory.getLogger(Application.class);

    private IBooksApi booksApi = new BooksApi();
    private JsonTransformer transformer = new JsonTransformer();

    @Override
    public void init() {

        before("/*", (request, response) -> {
            log.info("Request Received : "+request.pathInfo());
        });

        post("/books", booksApi.add(), transformer);
        get("/books/:id", booksApi.get(), transformer);
        get("/books", booksApi.getAll(), transformer);
        delete("/books/:id", booksApi.delete(), transformer);

        after("/*", (request, response) -> {
            log.info("Response returned : "+request.pathInfo());
        });
    }

    public static void main(String[] args) {
        new Application().init();
    }

}
