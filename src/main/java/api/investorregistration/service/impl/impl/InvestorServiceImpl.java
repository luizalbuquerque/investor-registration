package api.investorregistration.service.impl.impl;


import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.InvestorRepository;
import api.investorregistration.service.impl.InvestorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static api.investorregistration.utils.ConstantUtils.DUPLICATE_USER;

@Service
public class InvestorServiceImpl implements InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public void createInvestor(InvestorDto investorDto) {
        try {
            InvestorEntity userEntity = new InvestorEntity();
            userEntity.setEmail(investorDto.getEmail().trim());
            userEntity.setPassword(investorDto.getPassword().trim());
            investorRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(DUPLICATE_USER);
        }
    }

    @Override
    public Optional<InvestorEntity> findInvestorById(Long id) {
        return investorRepository.findById(id);
    }

    @Override
    public ResponseEntity<InvestorEntity> updateInvestor(long id) {
        return null;
    }

    @Override
    public InvestorDto updateByInvestorId(InvestorUpdateForm form, Long id) {

        Optional<InvestorEntity> op = investorRepository.findById(id);
        if (op.isPresent()) {
            InvestorEntity obj = op.get();
            if (form.getPassword() != null) {
                obj.setEmail(form.getEmail());
            }
            if (form.getEmail() != null) {
                obj.setPassword(form.getPassword());
            }
            investorRepository.save(obj);
            return convertToDto(obj);
        }
        return null;
    }

    // Converter ENTITY to DTO
    private InvestorDto convertToDto(InvestorEntity userEntity) {
        InvestorDto dto = new InvestorDto();
        dto.setId(userEntity.getId());
        dto.setEmail(userEntity.getEmail());
        dto.setPassword(userEntity.getPassword());
        return dto;
    }


    public void deleteById(Long id) {
        try {
            if (investorRepository.existsById(id)) {
                investorRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(DUPLICATE_USER);
        }
    }

}





