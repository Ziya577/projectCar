package com.example.service;

import com.example.dto.CarDto;
import com.example.entity.Car;
import com.example.exception.CarNotFoundException;
import com.example.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public  class CarService {


    private final ModelMapper mapper;

    private final CarRepository carRepository;

    public CarService( ModelMapper mapper, CarRepository carRepository) {

        this.mapper = mapper;
        this.carRepository = carRepository;
    }

    public List<CarDto>  getAllCars(){
        List<Car> source = carRepository.findAll();

        Type destination = new TypeToken<List<CarDto>>() {}.getType();
        return mapper.map(source, destination) ;

    }

    public CarDto createCar(Car car) {
        Car save = carRepository.save(car);
        return mapper.map(save, CarDto.class);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Optional<Car> editCar(Car car, Long id) {
        Optional<Car> foundedcar =carRepository.findById(id);
        if (foundedcar.isPresent()){
            Car result=foundedcar.get();
            result.setModel(car.getModel());
            result.setMarka(car.getMarka());
            result.setQiymeti(car.getQiymeti());
          return Optional.of(carRepository.save(result));
    }
           throw new CarNotFoundException("tapilmadi id");
    }

}

