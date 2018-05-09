package com.fiap.carautomation.controller;

import com.fiap.carautomation.dto.GeolocatorDTO;
import com.fiap.carautomation.enums.Status;
import com.fiap.carautomation.model.Car;
import com.fiap.carautomation.model.User;
import com.fiap.carautomation.service.CarService;
import com.fiap.carautomation.service.UserService;
import com.fiap.carautomation.utils.GoogleApiUtils;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/googleapi")
public class GeolocationController {

    private UserService userService;
    private CarService carService;

    @Autowired
    public GeolocationController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping
    public String teste() throws InterruptedException, ApiException, IOException {

        String end1 = "Avenida General teixeira lott,263,SP";
        String end2 = "Avenida paulista";

        DistanceMatrix result = GoogleApiUtils.getDistanceAndTime(end1,end2);

        String km = result.rows[0].elements[0].distance.humanReadable;
        String tempo = result.rows[0].elements[0].duration.humanReadable;

        return "Km :" + km + "Tempo :" + tempo;
    }
    @PostMapping("/chamarVeiculo")
    public String chamarVeiculo(@RequestBody GeolocatorDTO dto) throws InterruptedException, ApiException, IOException {
        Car car = carService.findById(dto.getUserId());
        User user = userService.findById(dto.getUserId());
        DistanceMatrix result = GoogleApiUtils.getDistanceAndTime(car.getEndereco(),user.getEnderecoOrigem());

        double distanciaPercorrida = result.rows[0].elements[0].distance.inMeters;

        user.setEnderecoDestino(dto.getUserAddressDestin());

        car.setEndereco(user.getEnderecoOrigem());
        car.setStatusCar(Status.COM_PASSAGEIRO);
        car.setQtdKmRodados(distanciaPercorrida);
        userService.update(user);
        carService.atualizar(car);

        return "Carro chegou ao seu destino : " + car.getEndereco() + "Km rodados : " + distanciaPercorrida;

    }
    @PostMapping("/goToDestin")
    public String goToDestin(@RequestBody  GeolocatorDTO dto) throws InterruptedException, ApiException, IOException {

        Car car = carService.findById(dto.getUserId());
        User user = userService.findById(dto.getUserId());
        if(!user.getEnderecoAtual().equalsIgnoreCase(car.getEndereco())){
            return "O carro : "+ car.getPlaca() + "Não chegou ao passageiro";
        }
        DistanceMatrix result = GoogleApiUtils.getDistanceAndTime(car.getEndereco(),user.getEnderecoDestino());

        double distanciaPercorrida = result.rows[0].elements[0].distance.inMeters;
        user.setEnderecoAtual(dto.getUserAddressDestin());
        car.setQtdKmRodados(car.getQtdKmRodados() + distanciaPercorrida);
        car.setStatusCar(Status.DISPONIVEL);

        return "Você chegou ao seu destino :" + user.getEnderecoAtual() + "Km rodados :" + distanciaPercorrida;

    }

}
