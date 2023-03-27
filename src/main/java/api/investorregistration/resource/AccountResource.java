package api.investorregistration.resource;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.dto.TransactionDTO;
import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public TransactionDTO updateById(@RequestBody TransactionDTO form, @PathVariable("id") Long id) {
        return accountService.deposit(form, id);
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
