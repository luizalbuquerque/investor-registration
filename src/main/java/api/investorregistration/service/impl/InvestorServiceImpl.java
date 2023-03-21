package api.investorregistration.service.impl;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.repository.InvestorRepository;
import api.investorregistration.service.AccountService;
import api.investorregistration.service.InvestorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static api.investorregistration.utils.ConstantUtils.DUPLICATE_USER;

@Service
public class InvestorServiceImpl implements InvestorService {

    private final InvestorRepository investorRepository;

    private AccountService accountService;

    public InvestorServiceImpl(InvestorRepository investorRepository, AccountRepository accountRepository) {
        this.investorRepository = investorRepository;
    }

    public void createInvestor(InvestorDto investorDto) {

        List<AccountEntity> accountEntityList = new ArrayList<>();

        try {

            // Create a new account
            AccountEntity accountEntity = new AccountEntity();
            accountEntity = generateNewAccountInvestor();

            // Adding new account in list
            accountEntityList.add(accountEntity);

            InvestorEntity investorEntity = new InvestorEntity();
            investorEntity.setIdInvestor(investorEntity.getIdInvestor());
            investorEntity.setEmail(investorDto.getEmail().trim());
            investorEntity.setCpf(investorDto.getCpf());
            investorEntity.setCnpj(investorDto.getCnpj());
            // TODO - atribuir account ao usuário
            investorRepository.save(investorEntity);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(DUPLICATE_USER);
        }
    }

    private AccountEntity generateNewAccountInvestor() {

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
            obj.setUpdatedAt(Instant.now());
            investorRepository.save(obj);
            return convertToDto(obj);
        }
        return null;
    }

    private InvestorDto convertToDto(InvestorEntity investorEntity) {
        InvestorDto dto = new InvestorDto();
        dto.setId(investorEntity.getIdInvestor());
        dto.setEmail(investorEntity.getEmail());
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





