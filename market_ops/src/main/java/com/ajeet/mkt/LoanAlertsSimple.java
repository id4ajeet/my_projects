
package com.ajeet.mkt;

import com.google.gson.Gson;

import com.ajeet.mkt.model.AccountDetails;
import com.ajeet.mkt.model.AccountPortfolio;
import com.ajeet.mkt.model.MarketResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class LoanAlertsSimple {

    public static void main(String[] args) throws IOException {
        //Check if input is provided
        if (args.length != 2) {
            System.out.println("Correct syntax \n$ ./myprogram2 marketdata.json loandata.json");
            return;
        }

        //Gson library for json transformation
        Gson gson = Utils.getGson();

        //get the Market data in map
        String marketData = Utils.readFile(args[0]);
        MarketResponse[] marketDetails = gson.fromJson(marketData, MarketResponse[].class);
        Map<String, Double> marketPrice = Arrays.stream(marketDetails)
            .collect(Collectors.toMap(MarketResponse::getIsin, MarketResponse::getPrice));

        //get the Account data in pojo
        String accountData = Utils.readFile(args[1]);
        AccountDetails[] accountDetails = gson.fromJson(accountData, AccountDetails[].class);

        //find non secured
        List<AccountPortfolio> nonSecured = Arrays.stream(accountDetails)
            .map(p -> {
                double sumPositions = p.getPositions()
                    .stream()
                    .mapToDouble(x -> x.getQuantity() * marketPrice.get(x.getId()))
                    .sum();

                return new AccountPortfolio(p.getId(), p.getCreditpolicy(), p.getAmount(), sumPositions);
            })
            .filter(p -> p.getAmount() > p.getEligible_collateral())
            .collect(Collectors.toList());

        //covert to json output
        String output = gson.toJson(nonSecured);

        //Print to console
        Utils.outputToConsole(output);

        //Write to file
        Utils.outputToFile("nonsecured.json",output);
    }
}
