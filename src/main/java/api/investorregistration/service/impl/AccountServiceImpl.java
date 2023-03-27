package api.investorregistration.service.impl;

import api.investorregistration.dto.TransactionDTO;
import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.TransactionEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.repository.TransactionRepository;
import api.investorregistration.service.AccountService;
import api.investorregistration.utils.AccountStatus;
import api.investorregistration.utils.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static api.investorregistration.utils.ConstantUtils.ACCOUNT_ALREADY_EXISTS;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    private AccountEntity accountEntity;

    @Autowired
    TransactionRepository transactionRepository;


    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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
    public AccountEntity deposit(TransactionDTO form, Long id) {

        List<TransactionEntity> transactionList = new ArrayList<>();
        Optional<AccountEntity> existentAccount = accountRepository.findById(id);

        if (existentAccount.isPresent()) {

            // Account setto os valores vindos do formulário ok
            AccountEntity updatedAccount = existentAccount.get();
            updatedAccount.setAmount(updatedAccount.getAmount() + form.getAmount());
            updatedAccount.setUpdatedAt(Instant.now());

            // Transacao seto os dados da transacao vindas do form também.
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setId(form.getAccountId());
            transactionEntity.setAmount(form.getAmount());
            transactionEntity.setDescription(form.getDescription());

            // Adcionando a minha lista de transacoes uma nova transacao
            transactionList.add(transactionEntity);

            // salvar minha transacao na repository de transacoes
            TransactionEntity transactionCreated = transactionRepository.save(transactionEntity);

            updatedAccount.setTransactionEntity(transactionList);

            AccountEntity accountsaved = accountRepository.save(updatedAccount);

            Optional<AccountEntity> optionalAccount = accountRepository.findById(accountsaved.getIdAccount());

            return ResponseEntity.ok().body(updatedAccount).getBody();
        }
        return null;
    }


    @Override
    public AccountEntity withdraw(TransactionDTO form, Long id) {

        List<TransactionEntity> transactionList = new ArrayList<>();
        Optional<AccountEntity> existentAccount = accountRepository.findById(id);

        if (existentAccount.isPresent()) {

            // Account sett os valores vindos do formulário ok
            AccountEntity updatedAccount = existentAccount.get();

            if(updatedAccount.getAmount() <= (updatedAccount.getAmount() + form.getAmount)){
                throw new RuntimeException("Insufficient account balance" + updatedAccount.getIdAccount());
            }
            updatedAccount.setAmount(updatedAccount.getAmount() - form.getAmount());
            updatedAccount.setUpdatedAt(Instant.now());

            // Transacao seto os dados da transacao vindas do form também.
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setId(form.getAccountId());
            transactionEntity.setAmount(form.getAmount());
            transactionEntity.setDescription(form.getDescription());

            // Adcionando a minha lista de transacoes uma nova transacao
            transactionList.add(transactionEntity);

            // salvar minha transacao na repository de transacoes
            TransactionEntity transactionCreated = transactionRepository.save(transactionEntity);

            updatedAccount.setTransactionEntity(transactionList);

            AccountEntity accountsaved = accountRepository.save(updatedAccount);

            Optional<AccountEntity> optionalAccount = accountRepository.findById(accountsaved.getIdAccount());

            return ResponseEntity.ok().body(updatedAccount).getBody();
        }
        return null;
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