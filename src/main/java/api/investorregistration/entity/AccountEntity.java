package api.investorregistration.entity;


import api.investorregistration.utils.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    private String amount;

    @Enumerated(EnumType.STRING)
    private AccountType type;


}
