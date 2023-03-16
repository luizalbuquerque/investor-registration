package api.investorregistration.service.impl;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.entity.InvestorEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    void createUser(InvestorDto investorDto);

    Optional<InvestorEntity> findUserById(Long id);

    ResponseEntity<InvestorEntity> updateUser(long id);

    InvestorDto updateByUserId(InvestorUpdateForm form, Long id);

    void deleteById(Long id);
}
