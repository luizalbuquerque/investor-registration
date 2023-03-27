package api.investorregistration.repository;

import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<TransactionEntity, Long> {

}
