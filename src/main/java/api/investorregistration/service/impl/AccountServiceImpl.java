package api.investorregistration.service.impl;

import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.TransactionEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.service.AccountService;
import api.investorregistration.service.TransactionService;
import api.investorregistration.utils.AccountStatus;
import api.investorregistration.utils.AccountType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static api.investorregistration.utils.ConstantUtils.ACCOUNT_ALREADY_EXISTS;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountEntity accountEntity;

    private final AccountService accountService;

    private final TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, AccountEntity accountEntity, AccountService accountService, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.accountEntity = accountEntity;
        this.accountService = accountService;
        this.transactionService = transactionService;
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
    public void deposit( double value, Long id) {
        accountRepository.updateDeposit(value, id);
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


    public AccountEntity verifyAccount(Long id) {
        return accountRepository.findAccountById(id);
    }

    public List<TransactionEntity> accountStatement(Long id) {
        AccountEntity account = verifyAccount(id);
        return transactionService.findAccounts(account);
    }

    public AccountEntity getAmount(Long id) {
        return accountRepository.findAccountById(id);
    }
}