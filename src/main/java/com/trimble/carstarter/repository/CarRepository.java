package com.trimble.carstarter.repository;

import com.trimble.carstarter.model.Car;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {}
