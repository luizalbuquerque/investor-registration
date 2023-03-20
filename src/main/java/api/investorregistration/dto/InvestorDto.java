package api.investorregistration.dto;


import api.investorregistration.entity.InvestorEntity;

public class InvestorDto {

    private Long id;
    private String email;
    private String document;

    public InvestorDto(Long id, String email, String document) {
        this.id = id;
        this.email = email;
        this.document = document;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}
