package api.investorregistration.resource;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.repository.InvestorRepository;
import api.investorregistration.service.impl.InvestorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investor")
public class InvestorResource {

    private final InvestorService investorService;

    private final InvestorRepository investorRepository;

    public InvestorResource(InvestorService investorService, InvestorRepository investorRepository) {
        this.investorService = investorService;
        this.investorRepository = investorRepository;
    }

    // Criando um verbo http post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody InvestorDto investorDto) {
        investorService.createInvestor(investorDto);
    }


    @GetMapping
    public List<InvestorEntity> list() {
        return investorRepository.findAll();
    }


    @GetMapping("/{id}")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Optional<InvestorEntity> findById(@PathVariable Long id) {
        return investorService.findInvestorById(id);
    }


    @PutMapping("/{id}")
    public InvestorDto updateById(@RequestBody InvestorUpdateForm form, @PathVariable("id") Long id) {
        return investorService.updateByInvestorId(form, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        investorService.deleteById(id);
    }


}
