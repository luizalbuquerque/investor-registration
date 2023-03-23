package api.investorregistration.resource;

import api.investorregistration.dto.TransactionDTO;
import api.investorregistration.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionResource {


//    private final TransactionService transactionService;
//
//    public TransactionResource(TransactionService transactionService) {
//        this.transactionService = transactionService;
//    }
//
//
//    @PostMapping("/deposit")
//    public ResponseEntity<?> deposit(@RequestHeader(name="Authorization")String token, @RequestBody TransactionDTO transactionDTO){
//        try {
//            TransactionDTO deposit = transactionService.deposit(token, transactionDTO);
//            return new ResponseEntity<>(deposit, HttpStatus.OK);
//        } catch (InvalidResultSetAccessException e) {
//            return new ResponseEntity<>("UNAUTHORIZED_ACCESS",HttpStatus.UNAUTHORIZED);
//        }
//    }


}
