
package com.ajeet.mkt;

import com.google.gson.Gson;

import com.ajeet.mkt.model.MarketRequest;
import com.ajeet.mkt.model.MarketResponse;

import java.io.IOException;
import java.util.Arrays;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ExtractMarketSimple {

    public static void main(String[] args) throws IOException {

        //Check if input is provided
        if (args.length != 1) {
            System.out.println("Please provide url with marketdata json");
            return;
        }

        //Gson library for json transformation
        Gson gson = Utils.getGson();

        //fetch the json string request over http
        String request = ClientBuilder.newClient()
            .target(args[0])
            .request()
            .accept(MediaType.APPLICATION_JSON)
            .get(String.class);

        //transform request string to pojo
        MarketRequest[] markets;
        if (request.trim().startsWith("[")) {
            markets = gson.fromJson(request, MarketRequest[].class);
        } else {
            markets = new MarketRequest[1];
            markets[0] = gson.fromJson(request, MarketRequest.class);
        }

        //Create filtered response based on input
        MarketResponse[] filteredData = Arrays.stream(markets).map(p ->
            new MarketResponse(p.getTicker(), p.getPrice(), p.getId())
        ).toArray(MarketResponse[]::new);

        //transform object to json
        String output = gson.toJson(filteredData);

        //print to console
        Utils.outputToConsole(output);

        //Write to file
        Utils.outputToFile("marketdata.json", output);

    }
}
