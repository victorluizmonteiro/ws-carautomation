package com.fiap.carautomation.controller;

import com.fiap.carautomation.enums.Status;
import com.fiap.carautomation.model.Car;
import com.fiap.carautomation.service.CarService;
import com.fiap.carautomation.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("car")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }
        @GetMapping("/available")
        public Response<Car> findAvailableCars() {
            Response<Car> response = new Response<>();

                List<Car> cars = carService.findCarByStatus(Status.DISPONIVEL);
                if(cars.isEmpty()){

                    response.getErrors().add("Não foram encontrados carros Disponíveis");
                    return response;
                }
                    response.setData(cars);
                    return response;




        }

        public ResponseEntity<Car>cadastrar(@RequestBody Car car){

        try{
            carService.save(car);
            return ResponseEntity.ok().body(car);
        }catch (Exception e){
          return  ResponseEntity.badRequest().body(car);        }

        }
}
