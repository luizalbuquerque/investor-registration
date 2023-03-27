package api.investorregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    public double getAmount;
    private Long accountId;
    private String description;
    private double amount;

    public double getAmount() {
            return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
