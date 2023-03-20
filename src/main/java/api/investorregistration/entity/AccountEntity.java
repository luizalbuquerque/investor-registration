package api.investorregistration.entity;

import api.investorregistration.utils.AccountStatus;
import api.investorregistration.utils.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

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

    @Column(name = "account_number",unique = true, nullable = false)
    private String accountNumber ;

    @Column
    private double amount;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "created")
    private Instant createdAt;

    @Column(name = "updated")
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private InvestorEntity investorEntity;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}
