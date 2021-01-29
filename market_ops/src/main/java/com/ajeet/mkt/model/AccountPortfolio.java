
package com.ajeet.mkt.model;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 * <pre>
 *   {
 *     "id": "loan1234",
 *     "creditpolicy": "policy1234",
 *     "amount": 10000,
 *     "eligible_collateral": 9999,
 *   }
 * </pre>
 */
public class AccountPortfolio {

    private String id;
    private String creditpolicy;
    private Double amount;
    private Double eligible_collateral;

    public AccountPortfolio(String id, String creditpolicy, Double amount, Double eligible_collateral) {
        this.id = id;
        this.creditpolicy = creditpolicy;
        this.amount = amount;
        this.eligible_collateral = eligible_collateral;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreditpolicy() {
        return creditpolicy;
    }

    public void setCreditpolicy(String creditpolicy) {
        this.creditpolicy = creditpolicy;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getEligible_collateral() {
        return eligible_collateral;
    }

    public void setEligible_collateral(Double eligible_collateral) {
        this.eligible_collateral = eligible_collateral;
    }

    @Override
    public String toString() {
        return "AccountPortfolio{" +
            "id='" + id + '\'' +
            ", creditpolicy='" + creditpolicy + '\'' +
            ", amount=" + amount +
            ", eligible_collateral=" + eligible_collateral +
            '}';
    }
}
