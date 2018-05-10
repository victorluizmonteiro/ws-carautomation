package com.fiap.carautomation.controller;

import com.fiap.carautomation.enums.Status;
import com.fiap.carautomation.model.Car;
import com.fiap.carautomation.service.CarService;
import com.fiap.carautomation.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

@Controller
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/available",method = RequestMethod.GET)
    public ResponseEntity<Response<Car>> findAvailableCars() {
        Response<Car> response = new Response<>();

        List<Car> cars = carService.findCarByStatus(Status.DISPONIVEL);
        if (cars.isEmpty()) {

            response.getErrors().add("Não foram encontrados carros Disponíveis");
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(cars);
        return ResponseEntity.ok().body(response);


    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Car> cadastrar(@RequestBody Car car) {

        try {
            carService.save(car);
            return ResponseEntity.ok().body(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(car);
        }

    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<Response<Car>>findAll(){
        Response<Car> response = new Response<>();
        List<Car>cars = carService.findAll();

        response.setData(cars);

        return  ResponseEntity.ok().body(response);

    }

    @RequestMapping(value = "/custoFrotaVeiculos",method = RequestMethod.GET)
    public ResponseEntity<String> calcularGastos(){

        List<Car> cars = carService.findAll();

        double totalKmRodados = cars.stream().mapToDouble(car -> car.getQtdKmRodados()).sum();
        Double custoKmRodado = totalKmRodados +(totalKmRodados* 0.9);
        String quebraLinha = System.getProperty("line.separator");

        String resposta =
                "O total de Kilometros percorridos pela frota é  : " + totalKmRodados + quebraLinha +
                "E o custo por Km percorrido é   de :" + custoKmRodado;
        return ResponseEntity.ok().body(resposta);

    }
}
