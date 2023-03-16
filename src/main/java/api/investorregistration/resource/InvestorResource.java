package api.investorregistration.resource;

import api.investorregistration.dto.InvestorDto;
import api.investorregistration.dto.InvestorUpdateForm;
import api.investorregistration.entity.InvestorEntity;
import api.investorregistration.repository.InvestorRepository;
import api.investorregistration.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class InvestorResource {

    private final UserService userService;

    private final InvestorRepository investorRepository;

    public InvestorResource(UserService userService, InvestorRepository investorRepository) {
        this.userService = userService;
        this.investorRepository = investorRepository;
    }

    // Criando um verbo http post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody InvestorDto investorDto) {
        userService.createUser(investorDto);
    }


    @GetMapping
    public List<InvestorEntity> list() {
        return investorRepository.findAll();
    }


    @GetMapping("/{id}")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Optional<InvestorEntity> findById(@PathVariable Long id) {
        return userService.findUserById(id);
    }


    @PutMapping("/{id}")
    public InvestorDto updateById(@RequestBody InvestorUpdateForm form, @PathVariable("id") Long id) {
        return userService.updateByUserId(form, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }


}
