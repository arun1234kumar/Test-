package com.trimble.carstarter.controller;

import com.trimble.carstarter.model.Car;
import com.trimble.carstarter.repository.CarRepository;
import com.trimble.carstarter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.ErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/cars")
@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "409", description = "Conflict occurred", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))) })

public class CarController {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register/{ownerId}")
    public Car registerCar(@RequestBody Car car, @PathVariable Long ownerId) {
        car.setOwner(userRepository.findById(ownerId).orElseThrow());
        return carRepository.save(car);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id) {
        return carRepository.findById(id).orElseThrow();
    }
}
