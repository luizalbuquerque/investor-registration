package api.investorregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "investor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InvestorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_investor")
    private Long idInvestor;

    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    private String document;

    @Column(name = "created")
    private Instant createdAt;

    @Column(name = "updated")
    private Instant updatedAt;

    @OneToMany
    private List<AccountEntity> accounts;

    public List<AccountEntity> getAccount() {
        return accounts;
    }

    public void setAccount(List<AccountEntity> accountEntity) {
        this.accounts = accountEntity;
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}

