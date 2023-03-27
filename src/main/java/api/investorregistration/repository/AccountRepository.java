package api.investorregistration.repository;

import api.investorregistration.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Modifying
    @Query("update AccountEntity c set c.amount = c.amount + ?1 where c.idAccount = ?2")
    void updateDeposit(double  value, Long id);

//    AccountEntity findByIdConta(Long id);

    @Modifying
    @Query("update AccountEntity c set c.amount = c.amount - ?1 where c.idAccount = ?2")
    void updateWithdraw(double  value, Long id);

    AccountEntity findAccountById(Long id);
}
