package com.ajeet.spark.sample;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class HelloWorldTest extends ITest {

    @Test
    public void testHelloWorld() {
        HelloWorld.controllers();

        when().request("GET", "/hello")
            .then().statusCode(200);
    }

    @Test
    public void testHelloWorldLog() {
        HelloWorld.controllers();

        given().log().all()
            .when().request("GET", "/hello")
            .then().log().all().statusCode(200);
    }

    @Test
    public void testHelloWorldResponse() {
        HelloWorld.controllers();

        Response response = get("/hello");

        Assert.assertNotNull(response);

        ResponseBody body = response.body();
        Assert.assertEquals("Hello World", body.asString());

        long timeInMS = response.time();
        Assert.assertTrue(timeInMS < 1000);
    }

    @Test
    public void testHelloWorldAssert() {
        HelloWorld.controllers();

        get("/hello")
            .then()
            .assertThat()
            .body(equalTo("Hello World"))
            .and()
            .assertThat()
            .time(is(lessThan(1000L)));
    }

    @Test
    public void testHelloWorldPost() {
        HelloWorld.controllers();

        Response response = with().body("Dummy Body")
            .when()
            .post("/hello/Ajeet");

        response.then()
            .log().all()
            .statusCode(200);

        Assert.assertEquals("Hello Ajeet Body sent is Dummy Body", response.body().asString());
    }

    @Test
    public void testHelloWorldWrongUrl() {
        HelloWorld.controllers();

        get("/hello/Ajeet")
            .then()
            .log().ifError()
            .statusCode(404);
    }
}

