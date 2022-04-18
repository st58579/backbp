package cz.upce.carsharing.controller;

import cz.upce.carsharing.model.dto.*;
import cz.upce.carsharing.model.Car;
import cz.upce.carsharing.model.Make;
import cz.upce.carsharing.model.Type;
import cz.upce.carsharing.service.CarsharingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carsharing")
@RequiredArgsConstructor
public class CarsharingController {

    private final CarsharingService carsharingService;

    @PostMapping("/filter")
    public CarResponse getAllCarsPaginatedAndFiltered(@RequestBody CarFilteredRequest request) {
        return carsharingService.getCarsPaginatedAndFiltered(request);
    }

    @PostMapping("/price/update/{carId}")
    public void updateCarPrice(@PathVariable(value="carId") Integer carId, @RequestBody UpdateCarPriceRequest updateCarPriceRequest){
        carsharingService.updateCarPrice(carId, Integer.valueOf(updateCarPriceRequest.getPrice()));
    }

    @GetMapping("/user/{id}")
    public CarResponse getUserCars(@PathVariable(value = "id") Integer userId){
        return carsharingService.getUserCars(userId);
    }
    @GetMapping("/rented/{id}")
    public RentedCarResponse getRentedUserCars(@PathVariable(value = "id") Integer userId){
        return carsharingService.getRentedUserCars(userId);
    }

    @GetMapping("/all/{id}")
    public CarResponse getAllCarsPaginated(@PathVariable(value ="id") Integer userId) {
        List<Car> cars = carsharingService.getAllCars(userId);
        return new CarResponse(cars, cars.size());
    }

    @GetMapping("/makes")
    public List<Make> getMakes() {
        return carsharingService.getMakes();
    }

    @GetMapping("/types")
    public List<Type> getTypes() {
        return carsharingService.getTypes();
    }

    @GetMapping("/{carId}")
    public Car getCarById(@PathVariable("carId") Integer carId) {
        return carsharingService.getCarById(carId);
    }

    @PostMapping(value = "/car/add")
    public void addCar(@RequestBody CarDto car) {
        carsharingService.addCar(car);
    }

    @PostMapping(value = "/car/update/availability")
    public Car updateCarAvailability(@RequestBody UpdateCarAvailabilityRequest request){
        return carsharingService.updateCarAvailability(request);
    }

    @PostMapping(value = "/type/add")
    public void addType(@RequestBody AddTypeRequest type){
        carsharingService.addType(type);
    }

    @PostMapping(value = "/make/add")
    public void addMake(@RequestBody AddMakeRequest make){
        carsharingService.addMake(make);
    }
}
