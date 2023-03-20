package api.investorregistration.repository;

import api.investorregistration.entity.InvestorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<InvestorEntity, Long> {

}
