package api.investorregistration.service;

import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AccountService {

    void generateNewAccount();
    void deposit(double value, Long id);
    void withdraw(double value, Long id);
    Optional<AccountEntity> findAccountById(Long id);
    List<TransactionEntity> accountStatement(Long id);
    AccountEntity getAmount(Long id);
}
