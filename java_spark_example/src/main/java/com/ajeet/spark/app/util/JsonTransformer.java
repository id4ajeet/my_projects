
package com.ajeet.spark.app.util;

import com.google.gson.Gson;

import spark.ResponseTransformer;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

    public <T> T render(Class cls, String json) {
        return (T) gson.fromJson(json, cls);
    }

}
