package api.investorregistration.resource;

import api.investorregistration.dto.TransactionDTO;
import api.investorregistration.entity.AccountEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static api.investorregistration.utils.ConstantUtils.ACCOUNT_WTHOUT_BALANCE;

@RestController
@RequestMapping("/api/account")
public class AccountResource {


    public AccountService accountService;
    private final AccountRepository accountRepository;

    public AccountResource(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    // TO create new account
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create() {
        accountService.generateNewAccount();
    }

    @GetMapping
    public List<AccountEntity> list() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Optional<AccountEntity> findById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }


    @PutMapping("/deposit/{id}")
    public AccountEntity updateById(@RequestBody TransactionDTO form, @PathVariable("id") Long id) {
        return accountService.deposit(form, id);
    }


    @PutMapping("/withdraw/{id}")
    public AccountEntity withdraw(@RequestBody TransactionDTO form, @PathVariable("id") Long id) {
        return accountService.withdraw(form, id);
    }
}
