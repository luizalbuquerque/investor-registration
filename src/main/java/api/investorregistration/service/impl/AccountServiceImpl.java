package api.investorregistration.service.impl;

import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.service.AccountService;
import api.investorregistration.utils.AccountStatus;
import api.investorregistration.utils.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Random;

import static api.investorregistration.utils.ConstantUtils.ACCOUNT_ALREADY_EXISTS;

public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public void generateNewAccount() {
        try {
            AccountEntity  accountEntity = new AccountEntity();
            accountEntity.setAccountNumber(generateNumberAccount());
            accountEntity.setAmount(0.0);
            accountEntity.setPassword(generatePassword());
            accountEntity.setType(AccountType.INVESTOR);
            accountEntity.setAccountStatus(AccountStatus.ACTIVE);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(ACCOUNT_ALREADY_EXISTS);
        }
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

}
