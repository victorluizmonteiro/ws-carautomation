package com.fiap.carautomation.controller;

import com.fiap.carautomation.dto.GeolocatorDTO;
import com.fiap.carautomation.enums.Status;
import com.fiap.carautomation.model.Car;
import com.fiap.carautomation.model.User;
import com.fiap.carautomation.service.CarService;
import com.fiap.carautomation.service.UserService;
import com.fiap.carautomation.utils.ConversorUtils;
import com.fiap.carautomation.utils.GoogleApiUtils;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/googleapi")
@Api(value = "geolocationapi",description = "Api que realiza as operações relacioandas ao Google Maps")
public class GeolocationController {

    private UserService userService;
    private CarService carService;

    @Autowired
    public GeolocationController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }


    @PostMapping(value = "/chamarVeiculo")
    @ApiOperation(value = "Recurso que  simula o carro indo até o cliente que o chamou ",response = String.class)
    public String chamarVeiculo(@RequestBody GeolocatorDTO dto) throws InterruptedException, ApiException, IOException {
        Car car = carService.findById(dto.getCarId());
        User user = userService.findById(dto.getUserId());

        if (car.getStatusCar() != Status.COM_PASSAGEIRO || car.getStatusCar() != Status.MANUTENCAO) {
            DistanceMatrix result = GoogleApiUtils.getDistanceAndTime(car.getEndereco(), user.getEnderecoOrigem());

            double distanciaPercorrida = result.rows[0].elements[0].distance.inMeters;
            String distanciaPercorridaKM = result.rows[0].elements[0].distance.humanReadable;

            user.setEnderecoDestino(dto.getUserAddressDestin());

            car.setEndereco(user.getEnderecoOrigem());
            car.setStatusCar(Status.COM_PASSAGEIRO);
            car.setQtdKmRodados(distanciaPercorrida);
            userService.update(user);
            carService.atualizar(car);

            return "Carro chegou ao seu destino : " + car.getEndereco() + ConversorUtils.getLineSeparator() +  "Km rodados : " + distanciaPercorridaKM;
        }

        return "O carro " + car.getPlaca() + "Está com passageiro ou em manutenção !";


    }

    @PostMapping(value = "/goToDestin")
    @ApiOperation(value = "Recurso que  simula o cliente indo até o destino final escolhido ",response = String.class)

    public String goToDestin(@RequestBody GeolocatorDTO dto) throws InterruptedException, ApiException, IOException {

        Car car = carService.findById(dto.getCarId());
        User user = userService.findById(dto.getUserId());
        if (!user.getEnderecoAtual().equalsIgnoreCase(car.getEndereco())) {
            return "O carro : " + car.getPlaca() + "Não chegou ao passageiro";
        }
        DistanceMatrix result = GoogleApiUtils.getDistanceAndTime(car.getEndereco(), user.getEnderecoDestino());

        double distanciaPercorrida = result.rows[0].elements[0].distance.inMeters;
        String distanciaPercorridaKM = result.rows[0].elements[0].distance.humanReadable;

        Double distanciaTotal = car.getQtdKmRodados() + distanciaPercorrida;
        user.setEnderecoAtual(user.getEnderecoDestino());
        car.setQtdKmRodados(ConversorUtils.convertMetersToKilometers(distanciaTotal));
        car.setStatusCar(Status.DISPONIVEL);
        userService.update(user);
        carService.atualizar(car);

        return "Você chegou ao seu destino : " + user.getEnderecoAtual() + ConversorUtils.getLineSeparator() +"Km rodados : " + distanciaPercorridaKM;

    }

}
