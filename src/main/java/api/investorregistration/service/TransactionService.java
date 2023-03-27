package api.investorregistration.service;

import api.investorregistration.dto.TransactionDTO;
import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.TransactionEntity;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TransactionService {

    public List<TransactionEntity> findAccounts(AccountEntity account);

}