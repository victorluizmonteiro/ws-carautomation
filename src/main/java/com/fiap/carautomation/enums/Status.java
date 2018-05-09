package com.fiap.carautomation.enums;

import java.io.Serializable;

public enum Status implements Serializable {

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
