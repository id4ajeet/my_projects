package com.ajeet.spark.sample;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class HelloWorld {

    /**
     * Run and call http://localhost:4567/hello
     *
     * @param args input
     */
    public static void main(String[] args) {
        controllers();
    }

    public static void controllers() {
        get("/hello", (req, res) -> "Hello World");

        post("/hello/:name", (req, res) -> "Hello " + req.params(":name") + " Body sent is " + req.body());
    }
}
