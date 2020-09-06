package com.ajeet.spark.app.util;

import com.ajeet.spark.app.model.Book;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class JsonTransformerTest{

    private JsonTransformer transformer = new JsonTransformer();

    @Test
    public void render() {
        String json = transformer.render(new Book("Ajeet S.", "Drinking Water"));
        assertNotNull(json);

        Book book = transformer.render(Book.class, json);
        assertEquals("Ajeet S.", book.getAuthor());
        assertEquals("Drinking Water", book.getTitle());
    }
}
