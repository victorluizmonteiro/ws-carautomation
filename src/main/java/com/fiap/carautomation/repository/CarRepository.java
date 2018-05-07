package com.fiap.carautomation.repository;

import com.fiap.carautomation.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
}
