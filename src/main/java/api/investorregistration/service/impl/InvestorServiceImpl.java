package api.investorregistration.service.impl;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.entity.AccountEntity;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.exceptions.BusinessException;
import api.investorregistration.repository.AccountRepository;
import api.investorregistration.repository.InvestorRepository;
import api.investorregistration.service.InvestorService;
import api.investorregistration.utils.AccountStatus;
import api.investorregistration.utils.AccountType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import static api.investorregistration.utils.ConstantUtils.ACCOUNT_ALREADY_EXISTS;
import static api.investorregistration.utils.ConstantUtils.DUPLICATE_USER;

@Service
public class InvestorServiceImpl implements InvestorService {

    private final InvestorRepository investorRepository;
    private final AccountRepository accountRepository;

    public InvestorServiceImpl(InvestorRepository investorRepository, AccountRepository accountRepository) {
        this.investorRepository = investorRepository;
        this.accountRepository = accountRepository;
    }

    public void createInvestor(InvestorDto investorDto) {
        try {
            InvestorEntity investorEntity = new InvestorEntity();
            investorEntity.setEmail(investorDto.getEmail().trim());
            investorEntity.setCpf(investorDto.getCpf());
            investorEntity.setCnpj(investorDto.getCnpj());
            InvestorEntity investorEntityCreated = investorRepository.save(investorEntity);
            generateNewAccountInvestor(investorEntityCreated);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(DUPLICATE_USER);
        }
    }

    private void generateNewAccountInvestor(InvestorEntity investorEntity) {
        try {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setAccountNumber(generateNumberAccount());
            accountEntity.setAmount(0.0);
            accountEntity.setPassword(generatePassword());
            accountEntity.setType(AccountType.INVESTOR);
            accountEntity.setAccountStatus(AccountStatus.ACTIVE);
            accountEntity.setCreatedAt(Instant.now());
            accountEntity.setInvestorEntity(investorEntity);
            accountRepository.save(accountEntity);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(ACCOUNT_ALREADY_EXISTS);
        }
    }

    @Override
    public Optional<InvestorEntity> findInvestorById(Long id) {
        return investorRepository.findById(id);
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

    public String generateNumberAccount() {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            text.append(random.nextInt(12));
        }
        return text.toString();
    }

    public String generatePassword() {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            text.append(random.nextInt(4));
        }
        return text.toString();
    }
}