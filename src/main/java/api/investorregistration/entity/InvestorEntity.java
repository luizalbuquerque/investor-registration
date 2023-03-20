package api.investorregistration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
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

    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @CPF
    @CNPJ
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

