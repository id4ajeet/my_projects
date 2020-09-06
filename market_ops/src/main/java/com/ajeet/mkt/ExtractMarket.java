
package com.ajeet.mkt;

import com.google.gson.Gson;

import com.ajeet.mkt.model.MarketRequest;
import com.ajeet.mkt.model.MarketResponse;

import java.util.Arrays;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ExtractMarket {

    private String marketDataUrl;
    private Gson gson;

    public ExtractMarket(String marketDataUrl) {
        this.marketDataUrl = marketDataUrl;
        this.gson = Utils.getGson();
    }

    /**
     * @return Json Request fetched from market-data url
     */
    private String fetchRequest() {
        return ClientBuilder.newClient()
            .target(marketDataUrl)
            .request()
            .accept(MediaType.APPLICATION_JSON)
            .get(String.class);
    }

    /**
     * transform request String to pojo array, then creates response from pojo array
     */
    private MarketResponse[] convertToPojo(String request) {
        MarketRequest[] markets;
        if (request.trim().startsWith("[")) {
            markets = gson.fromJson(request, MarketRequest[].class);
        } else {
            markets = new MarketRequest[1];
            markets[0] = gson.fromJson(request, MarketRequest.class);
        }

        MarketResponse[] filteredData = Arrays.stream(markets).map(p ->
            new MarketResponse(p.getTicker(), p.getPrice(), p.getId())
        ).toArray(MarketResponse[]::new);

        return filteredData;
    }

    private String createOutputJson(MarketResponse[] markets) {
        return gson.toJson(markets);
    }

    public void execute(String fileName) {

        String request = fetchRequest();

        MarketResponse[] markets = convertToPojo(request);

        String output = createOutputJson(markets);

        Utils.outputToConsole(output);
        Utils.outputToFile(fileName, output);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide url with marketdata json");
            return;
        }

        ExtractMarket obj = new ExtractMarket(args[0]);
        obj.execute("marketdata.json");
    }
}
