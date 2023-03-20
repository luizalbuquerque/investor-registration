package api.investorregistration.resource;

import api.investorregistration.repository.AccountRepository;
import api.investorregistration.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AccountResource {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    public AccountResource(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create() {
        accountService.generateNewAccount();
    }

    //operações: Saque, Depósito, Transferência e extrato
}
