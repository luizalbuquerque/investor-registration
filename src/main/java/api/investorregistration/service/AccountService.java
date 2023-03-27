package api.investorregistration.service;

import api.investorregistration.dto.TransactionDTO;
import api.investorregistration.entity.AccountEntity;

import java.util.Optional;


public interface AccountService {

    void generateNewAccount();
    AccountEntity deposit(TransactionDTO form, Long id);

    AccountEntity withdraw(TransactionDTO form, Long id);

    Optional<AccountEntity> findAccountById(Long id);
}
