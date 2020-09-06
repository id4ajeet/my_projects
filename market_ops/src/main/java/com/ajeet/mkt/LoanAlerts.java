
package com.ajeet.mkt;

import com.google.gson.Gson;

import com.ajeet.mkt.model.AccountDetails;
import com.ajeet.mkt.model.AccountPortfolio;
import com.ajeet.mkt.model.MarketResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class LoanAlerts {

    private final String marketFile;
    private final String accountFile;
    private final Gson gson;

    public LoanAlerts(String marketFile, String accountFile) {
        this.marketFile = marketFile;
        this.accountFile = accountFile;
        this.gson = Utils.getGson();
    }

    /**
     * get the Account data in pojo
     */
    private AccountDetails[] getAccountDetails() {
        String accountData = Utils.readFile(accountFile);
        return gson.fromJson(accountData, AccountDetails[].class);
    }

    /**
     * get the Market data in map
     */
    private Map<String, Double> getMarketPriceMap() {
        String marketData = Utils.readFile(marketFile);
        MarketResponse[] marketDetails = gson.fromJson(marketData, MarketResponse[].class);
        return Arrays.stream(marketDetails)
            .collect(Collectors.toMap(MarketResponse::getIsin, MarketResponse::getPrice));
    }

    /**
     * find non secured portfolios
     */
    private List<AccountPortfolio> findNonSecured(Map<String, Double> marketPrice, AccountDetails[] accountDetails) {
        return Arrays.stream(accountDetails)
            .map(p -> {
                double sumPositions = p.getPositions()
                    .stream()
                    .mapToDouble(x -> x.getQuantity() * marketPrice.get(x.getId()))
                    .sum();

                return new AccountPortfolio(p.getId(), p.getCreditpolicy(), p.getAmount(), sumPositions);
            })
            .filter(p -> p.getAmount() > p.getEligible_collateral())
            .collect(Collectors.toList());
    }

    /**
     * covert to json output
     */
    private String createOutputJson(List<AccountPortfolio> nonSecured) {
        return gson.toJson(nonSecured);
    }

    public void execute(String filename) {
        Map<String, Double> marketPrice = getMarketPriceMap();

        AccountDetails[] accountDetails = getAccountDetails();

        List<AccountPortfolio> nonSecured = findNonSecured(marketPrice, accountDetails);

        String output = createOutputJson(nonSecured);

        Utils.outputToConsole(output);

        Utils.outputToFile(filename, output);
    }

    public static void main(String[] args) {
        //Check if input is provided
        if (args.length != 2) {
            System.out.println("Correct syntax \n$ ./myprogram2 marketdata.json loandata.json");
            return;
        }

        LoanAlerts obj = new LoanAlerts(args[0], args[1]);
        obj.execute("nonsecured.json");
    }
}
