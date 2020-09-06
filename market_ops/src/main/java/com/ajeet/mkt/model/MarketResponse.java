
package com.ajeet.mkt.model;

/**
 * @author <a href="mailto:AjeetKumar.Singh1@britebill.com">Ajeet</a>
 */
public class MarketResponse {

    private String ticker;
    private Double price;
    private String isin;

    public MarketResponse(String ticker, Double price, String isin) {
        this.ticker = ticker;
        this.price = price;
        this.isin = isin;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Override
    public String toString() {
        return "MarketResponse{" +
            "ticker='" + ticker + '\'' +
            ", price=" + price +
            ", isin='" + isin + '\'' +
            '}';
    }
}
