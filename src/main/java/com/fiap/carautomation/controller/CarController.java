package com.fiap.carautomation.controller;

import com.fiap.carautomation.dto.CarDTO;
import com.fiap.carautomation.enums.Status;
import com.fiap.carautomation.model.Car;
import com.fiap.carautomation.service.CarService;
import com.fiap.carautomation.utils.ConversorUtils;
import com.fiap.carautomation.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/car")
@Api(value = "carapi", description = "Api responsável por realizar operações relacionadas ao carro.")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/available")
    @ApiOperation(value = "Recurso responsável por buscar todos os carros Disponíveis", response = Car.class)
    public ResponseEntity<Response<Car>> findAvailableCars() {
        Response<Car> response = new Response<>();

        List<Car> cars = carService.findCarByStatus(Status.DISPONIVEL);
        if (cars.isEmpty()) {

            response.getErrors().add("Não foram encontrados carros Disponíveis");
            return ResponseEntity.badRequest().body(response);
        }
        response.setDataList(cars);
        return ResponseEntity.ok().body(response);


    }

    @PostMapping
    @ApiOperation(value = "Recurso responsável por cadastrar veiculos", response = Car.class)
    public ResponseEntity<Response<Car>> cadastrar(@Valid @RequestBody CarDTO carDTO, BindingResult result) {

        Response<Car> response = new Response();
        Car car = carService.convertDtoToCar(carDTO);


        if (result.hasErrors()) {
            result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);

        } else {
            carService.save(car);
            response.setData(car);
            return ResponseEntity.ok().body(response);
        }


    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "Recurso responsável por buscar todos os veiculos", response = Car.class)
    public ResponseEntity<Response<Car>> findAll() {
        List<Car> cars = carService.findAll();

        Response<Car> response = new Response();
        response.setDataList(cars);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "/custoFrotaVeiculos")
    @ApiOperation(value = "Recurso responsável por calcular o custo por km rodado de toda a frota de veiculos", response = String.class)
    public ResponseEntity<String> calcularGastos() {

        List<Car> cars = carService.findAll();

        double totalKmRodados = cars.stream().mapToDouble(car -> car.getQtdKmRodados().doubleValue()).sum();
        Double custoKmRodado = totalKmRodados + (totalKmRodados * 0.9);

        String resposta =
                "O total de Kilometros percorridos pela frota é  : " + totalKmRodados + ConversorUtils.getLineSeparator() +
                        "E o custo em reais por Km percorrido é   de :" + custoKmRodado;
        return ResponseEntity.ok().body(resposta);

    }


}
