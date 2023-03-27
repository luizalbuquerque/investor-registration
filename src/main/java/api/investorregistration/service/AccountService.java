package api.investorregistration.service;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.dto.TransactionDTO;
import api.investorregistration.entity.AccountEntity;

import java.util.Optional;


public interface AccountService {

    void generateNewAccount();
    TransactionDTO deposit(TransactionDTO form, Long id);
    void withdraw(double value, Long id);

    Optional<AccountEntity> findAccountById(Long id);
}
