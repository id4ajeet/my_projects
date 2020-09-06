
package com.ajeet.mkt.model;

import java.util.List;

/**
 * @author <a href="mailto:AjeetKumar.Singh1@britebill.com">Ajeet</a>
 * <pre>
 *     {
 *     "amount": 533492,
 *     "creditpolicy": "policy2",
 *     "id": "loan0",
 *     "positions": [
 *       {
 *         "id": "US35906A1088",
 *         "quantity": 4736
 *       },
 *       {
 *         "id": "US9140901052",
 *         "quantity": 97002
 *       }]
 *       }
 * </pre>
 */
public class AccountDetails {

    private Double amount;
    private String creditpolicy;
    private String id;
    private List<Position> positions;

    public AccountDetails(Double amount, String creditpolicy, String id, List<Position> positions) {
        this.amount = amount;
        this.creditpolicy = creditpolicy;
        this.id = id;
        this.positions = positions;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreditpolicy() {
        return creditpolicy;
    }

    public void setCreditpolicy(String creditpolicy) {
        this.creditpolicy = creditpolicy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
            "amount=" + amount +
            ", creditpolicy='" + creditpolicy + '\'' +
            ", id='" + id + '\'' +
            ", positions=" + positions +
            '}';
    }
}
