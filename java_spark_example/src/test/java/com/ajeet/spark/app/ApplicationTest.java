package com.ajeet.spark.app;

import com.ajeet.spark.app.model.Book;
import com.ajeet.spark.sample.ITest;

import org.junit.Test;

import java.util.Map;

import io.restassured.response.Response;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ApplicationTest extends ITest {

    @Test
    public void testBookAdd() {
        new Application().init();

        Response response = with().body("{\"author\":\"Ajeet S.\",\"title\":\"Drinking Water\"}")
            .when()
            .post("/books");
        response.then()
            .log().all()
            .statusCode(200);

        Map<String, String> result = response.body().as(Map.class);
        assertEquals("success", result.get("status"));

        //Test get api
        Response getResp = when()
            .get("/books/1");
        getResp
            .then()
            .log().all()
            .statusCode(200);

        Book book = getResp.body().as(Book.class);

        assertNotNull(book);
    }
}
