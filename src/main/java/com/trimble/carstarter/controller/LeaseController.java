package com.trimble.carstarter.controller;

import com.trimble.carstarter.model.Car;
import com.trimble.carstarter.model.Car.CarStatus;
import com.trimble.carstarter.model.Lease;
import com.trimble.carstarter.model.User;
import com.trimble.carstarter.repository.CarRepository;
import com.trimble.carstarter.repository.LeaseRepository;
import com.trimble.carstarter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/lease")
public class LeaseController {
    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/start/{carId}/{userId}")
    public Lease startLease(@PathVariable Long carId, @PathVariable Long userId) {
        Car car = carRepository.findById(carId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        if (leaseRepository.findByCustomer(user).stream().filter(l -> l.getEndDate() == null).count() >= 2) {
            throw new RuntimeException("Customer cannot have more than 2 active leases.");
        }

        car.setStatus(CarStatus.ON_LEASE);
        carRepository.save(car);

        Lease lease = new Lease();
        lease.setCar(car);
        lease.setCustomer(user);
        lease.setStartDate(LocalDateTime.now());

        return leaseRepository.save(lease);
    }

    @PostMapping("/end/{leaseId}")
    public Lease endLease(@PathVariable Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId).orElseThrow();
        lease.setEndDate(LocalDateTime.now());

        Car car = lease.getCar();
        car.setStatus(CarStatus.IDLE);
        carRepository.save(car);

        return leaseRepository.save(lease);
    }

    @GetMapping("/history/user/{userId}")
    public List<Lease> getUserHistory(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return leaseRepository.findByCustomer(user);
    }
}
