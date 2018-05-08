package com.fiap.carautomation.model;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String placa;
    private String endereco;
    private double qtdKmRodados;

    public double getQtdKmRodados() {
        return qtdKmRodados;
    }

    public void setQtdKmRodados(double qtdKmRodados) {
        this.qtdKmRodados = qtdKmRodados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
