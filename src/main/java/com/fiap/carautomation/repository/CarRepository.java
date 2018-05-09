package com.fiap.carautomation.repository;

import com.fiap.carautomation.enums.Status;
import com.fiap.carautomation.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car,String> {

    List<Car>findAllByStatusCar(Status statusCar);
}
