package api.investorregistration.entity;


import api.investorregistration.utils.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long idAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    private InvestorEntity investorEntity;

    @Column
    private String amount;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createDate;


}
