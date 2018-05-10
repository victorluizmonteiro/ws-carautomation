package com.fiap.carautomation.model;

import com.fiap.carautomation.enums.Status;
import com.fiap.carautomation.utils.ConversorUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Car {
    @Id

    private String id;
    private String placa;
    private String endereco;
    private double qtdKmRodados;
    private Status statusCar;

    public Status getStatusCar() {
        return statusCar;
    }

    public void setStatusCar(Status statusCar) {
        this.statusCar = statusCar;
    }



    public double getQtdKmRodados() {
        return qtdKmRodados;
    }

    public void setQtdKmRodados(double qtdKmRodados) {
        this.qtdKmRodados = qtdKmRodados;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
