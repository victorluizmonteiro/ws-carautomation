package com.fiap.carautomation.service;

import com.fiap.carautomation.dto.CarDTO;
import com.fiap.carautomation.enums.Status;
import com.fiap.carautomation.model.Car;
import com.fiap.carautomation.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public List<Car> findCarByStatus(Status statusCar){

        List<Car>availableCars = carRepository.findAllByStatusCar(statusCar);

        return availableCars;
    }

    public Car findById(String id){

        Optional<Car> car = carRepository.findById(id);
        return car.get();
    }

    public void atualizar(Car car){

        carRepository.save(car);

    }

    public void save(Car car) {
        car.setStatusCar(Status.DISPONIVEL);
        carRepository.save(car);
    }

    public List<Car>findAll(){
        List<Car> cars = carRepository.findAll();

        return cars;
    }

    public Car convertDtoToCar(CarDTO dto){
        Car car = new Car();
        car.setId(dto.getId());
        car.setPlaca(dto.getPlaca());
        car.setStatusCar(dto.getStatusCar());
        car.setEndereco(dto.getEndereco());
        car.setQtdKmRodados(0.0);

        return  car;

    }
}
