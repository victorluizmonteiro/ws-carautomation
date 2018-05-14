package com.fiap.carautomation.dto;

import com.fiap.carautomation.enums.Status;

import javax.validation.constraints.NotEmpty;

public class CarDTO {
    private String id;
    @NotEmpty(message = "A placa é obrigatória !")
    private String placa;
    @NotEmpty(message = "O campo endereço é obrigatório !")
    private String endereco;
    private Status statusCar;

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

    public Status getStatusCar() {
        return statusCar;
    }

    public void setStatusCar(Status statusCar) {
        this.statusCar = statusCar;
    }
}
