package api.investorregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

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

    @CPF
    @Column(unique = true)
    private String cpf;

    @CNPJ
    @Column(unique = true)
    private String cnpj;

    @Column(name = "created")
    private Instant createdAt;

    @Column(name = "updated")
    private Instant updatedAt;

    @OneToMany
    private List<AccountEntity> accountEntity;

    public List<AccountEntity> getAccount() {
        return accountEntity;
    }

    public void setAccount(List<AccountEntity> accountEntity) {
        this.accountEntity = accountEntity;
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

