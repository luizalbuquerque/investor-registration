package api.investorregistration.resource;

import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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


    // Deposit
    @PutMapping("deposit/{value}/{id}")
    public ResponseEntity<?> deposit(@PathVariable double value, @PathVariable Long id){
        this.accountService.deposit(value, id);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    // Withdraw
    @PutMapping("withdraw/{value}/{id}")
    public ResponseEntity<?> withdraw(@PathVariable double value, @PathVariable Long id){
        if (value <= 0) {
            throw new BusinessException(ACCOUNT_WTHOUT_BALANCE);
        }
        this.accountService.withdraw(value, id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
