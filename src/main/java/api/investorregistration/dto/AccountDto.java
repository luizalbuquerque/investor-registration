package api.investorregistration.dto;


import javax.persistence.*;

public class AccountDto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    private InvestorDto investor;

}
