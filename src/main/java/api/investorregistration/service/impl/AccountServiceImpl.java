package api.investorregistration.service.impl;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.dto.TransactionDTO;
import api.investorregistration.dto.TransactionStatus;
import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.entity.TransactionEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.service.AccountService;
import api.investorregistration.utils.AccountStatus;
import api.investorregistration.utils.AccountType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static api.investorregistration.utils.ConstantUtils.ACCOUNT_ALREADY_EXISTS;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private AccountEntity accountEntity;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void generateNewAccount() {
        try {
            AccountEntity  accountEntity = new AccountEntity();
            accountEntity.setAccountNumber(generateNumberAccount());
            accountEntity.setAmount(0.0);
            accountEntity.setPassword(generatePassword());
            accountEntity.setType(AccountType.INVESTOR);
            accountEntity.setAccountStatus(AccountStatus.ACTIVE);
            accountEntity.setCreatedAt(Instant.now());
            accountRepository.save(accountEntity);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(ACCOUNT_ALREADY_EXISTS);
        }
    }

    @Override
    public TransactionDTO deposit(TransactionDTO form, Long id) {

        Optional<AccountEntity> existentAccount = accountRepository.findById(id);
        if (existentAccount.isPresent()) {
            AccountEntity updatedAccount = existentAccount.get();
            updatedAccount.setAmount(updatedAccount.getAmount() + form.getAmount());
            updatedAccount.setUpdatedAt(Instant.now());
            //updatedAccount.setTransactionEntity((List<TransactionEntity>) new TransactionDTO(form.getAccountId(), form.getDescription(), form.getAmount()));
            accountRepository.save(updatedAccount);
            }
        return form;
    }


    @Override
    public void withdraw( double value, Long id) {
        accountRepository.updateWithdraw(value, id);
    }

    public String generateNumberAccount() {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            text.append(random.nextInt(12));
        }
        return text.toString();
    }

    public String generatePassword() {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            text.append(random.nextInt(4));
        }
        return text.toString();
    }

    @Override
    public Optional<AccountEntity> findAccountById(Long id) {
        return accountRepository.findById(id);
    }
}