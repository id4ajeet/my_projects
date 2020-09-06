
package com.ajeet.spark.sample;

import com.ajeet.spark.app.util.JsonTransformer;

import org.junit.Before;

import io.restassured.RestAssured;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ITest {

    public JsonTransformer transformer = new JsonTransformer();

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4567;
    }
}
