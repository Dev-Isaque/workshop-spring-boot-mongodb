package com.isaquesoares.workshopmongo.model.dto;

import com.isaquesoares.workshopmongo.model.User;

public class UserDTO {
    private String id;
    private String name;
    private String cpf;
    private String dataNasc;
    private String sexo;
    private String telefone;
    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.dataNasc = user.getDataNasc();
        this.sexo = user.getSexo();
        this.telefone = user.getTelefone();
        this.cep = user.getCep();
        this.endereco = user.getEndereco();
        this.bairro = user.getBairro();
        this.cidade = user.getCidade();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
