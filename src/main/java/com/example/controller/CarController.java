package com.example.controller;

import com.example.dto.CarDto;
import com.example.entity.Car;
import com.example.exception.CarNotFoundException;
import com.example.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    private final ModelMapper modelMapper;
    private final CarService carService;

    public CarController(ModelMapper modelMapper, CarService carService) {
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @GetMapping("/get")
    public ResponseEntity< List<CarDto>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(),(HttpStatus.OK));

    }
   


    @PostMapping("/create")
    public CarDto createCar(@RequestBody CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        return carService.createCar(car);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }


    @PutMapping("/edit/{id}")
    public Optional<Car> editCar(@RequestBody Car car, @PathVariable Long id) {
        return carService.editCar(car, id);


    }
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<String> handleCarNotFoundException(CarNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);

    }
}

