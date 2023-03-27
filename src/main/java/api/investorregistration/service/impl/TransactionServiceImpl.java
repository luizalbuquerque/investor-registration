package api.investorregistration.service.impl;

import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.TransactionEntity;
import api.investorregistration.repository.TransactionRepository;
import api.investorregistration.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

        @Autowired
        private TransactionRepository transactionRepository;

        public List<TransactionEntity> findAccounts(AccountEntity account) {
            return transactionRepository.findByAccountIn(account);
        }

    }
