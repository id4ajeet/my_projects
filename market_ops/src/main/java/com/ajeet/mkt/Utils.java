
package com.ajeet.mkt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class Utils {

    public static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
            if (src == src.longValue())
                return new JsonPrimitive(src.longValue());
            return new JsonPrimitive(src);
        });
        return builder.create();
    }

    public static String readFile(String marketFile){
        try {
            return new String(Files.readAllBytes(Paths.get(marketFile)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * writes the output to file
     */
    public static void outputToFile(String fileName, String output) {

        try {
            byte[] bytes = output.getBytes();
            Path path = Paths.get(fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints the output to console
     */
    public static void outputToConsole(String output) {
        System.out.println(output);
    }
}
