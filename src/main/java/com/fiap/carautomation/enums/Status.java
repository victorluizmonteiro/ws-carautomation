package com.fiap.carautomation.enums;

public enum Status {

    DISPONIVEL("Disponível"),
    MANUTENCAO("Manutenção"),
    COM_PASSAGEIRO("Com Passageiro");


    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
