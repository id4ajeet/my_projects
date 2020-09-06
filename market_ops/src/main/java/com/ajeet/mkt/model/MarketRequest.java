
package com.ajeet.mkt.model;

/**
 * @author <a href="mailto:AjeetKumar.Singh1@britebill.com">Ajeet</a>
 *
 * "currency": "USD", "ticker": "H", "exchange": "USNYSE", "id": "US4485791028", "price": 51.3100, "name": "Hyatt Hotels
 * Corporation"
 */
public class MarketRequest {

    private String id;
    private String name;
    private String ticker;
    private String exchange;
    private Double price;
    private String currency;

    public MarketRequest(String id, String name, String ticker, String exchange, Double price, String currency) {
        this.id = id;
        this.name = name;
        this.ticker = ticker;
        this.exchange = exchange;
        this.price = price;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "MarketRequest{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", ticker='" + ticker + '\'' +
            ", exchange='" + exchange + '\'' +
            ", price=" + price +
            ", currency='" + currency + '\'' +
            '}';
    }
}
