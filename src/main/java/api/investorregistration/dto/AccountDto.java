package api.investorregistration.dto;


import javax.persistence.*;

public class AccountDto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idAccount;

    private InvestorDto investor;

}
