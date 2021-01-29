
package com.ajeet.mkt.model;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 * <pre>
 *     {
 *         "id": "US9140901052",
 *         "quantity": 97002
 *     }
 * </pre>
 */
public class Position {
    private String id;
    private Double quantity;

    public Position(String id, Double quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
