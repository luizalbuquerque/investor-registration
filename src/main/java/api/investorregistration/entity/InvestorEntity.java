package api.investorregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @OneToMany
    private List<AccountEntity> accounts;

    public List<AccountEntity> getAccount() {
        return accounts;
    }

    public void setAccount(List<AccountEntity> accountEntity) {
        this.accounts = accountEntity;
    }

}

