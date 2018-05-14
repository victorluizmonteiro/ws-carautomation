package com.fiap.carautomation.dto;

import javax.validation.constraints.NotEmpty;

public class UserDTO {
    private String id;
    private String nome;
    private String cpf;
    @NotEmpty(message = "Endereço obrigatório")
    private String enderecoOrigem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public void setEnderecoOrigem(String enderecoOrigem) {
        this.enderecoOrigem = enderecoOrigem;
    }
}
