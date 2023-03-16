package api.investorregistration.dto;


import api.investorregistration.entity.InvestorEntity;

public class InvestorDto {

    private Long id;
    private String email;
    private String password;

    public InvestorDto(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public InvestorDto(InvestorEntity userEntity) {
    }

    public InvestorDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
