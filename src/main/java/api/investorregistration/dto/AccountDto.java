package api.investorregistration.dto;


import javax.persistence.*;

public class AccountDto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long accountId;

    private InvestorDto investor;

}
