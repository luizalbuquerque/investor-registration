package api.investorregistration.service;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.entity.InvestorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface InvestorService {
    void createInvestor(InvestorDto investorDto);

    Optional<InvestorEntity> findInvestorById(Long id);

    ResponseEntity<InvestorEntity> updateInvestor(long id);

    void deleteById(Long id);

    InvestorDto updateByInvestorId(InvestorUpdateForm form, Long id);


}
