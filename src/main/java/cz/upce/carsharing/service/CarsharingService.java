package cz.upce.carsharing.service;

import cz.upce.carsharing.dto.*;
import cz.upce.carsharing.model.Car;
import cz.upce.carsharing.model.Make;
import cz.upce.carsharing.model.Type;
import cz.upce.carsharing.repository.CarsharingDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsharingService {

    private final CarsharingDao carsharingDao;

    public Car getCarById(Integer carId){
        return carsharingDao.getCarById(carId);
    }

    public List<Make> getMakes() {
        return carsharingDao.getMakes();
    }

    public List<Type> getTypes() {
        return carsharingDao.getTypes();
    }

    public List<Car> getAllCars(){
        return carsharingDao.getAllCars();
    }

    public void addCar(CarDto car) {
        carsharingDao.addCar(car);
    }

    public CarResponse getCarsPaginatedAndFiltered(CarFilteredRequest request) {
        if(request.getMakeId() > 0 && request.getTypeId() > 0){
            return carsharingDao.getCarsPaginatedAndFilteredByMakeAndType(request);
        } else if(request.getMakeId() > 0 && request.getTypeId() == 0){
            return carsharingDao.getCarsPaginatedAndFilteredByMake(request.getPage(),request.getLimit(),request.getMakeId(), request.getUserId());
        } else if(request.getMakeId() == 0 && request.getTypeId() > 0){
            return carsharingDao.getCarsPaginatedAndFilteredByType(request.getPage(),request.getLimit(),request.getTypeId(), request.getUserId());
        }
        return carsharingDao.getCarsPaginated(request.getPage(), request.getLimit(), request.getUserId());
    }

    public CarResponse getUserCars(Integer userId) {
        return carsharingDao.getUserCars(userId);
    }

    public void updateCarPrice(Integer carId, Integer price) {
        carsharingDao.updateCarPrice(carId, price);
    }

    public RentedCarResponse getRentedUserCars(Integer userId) {
        return carsharingDao.getRentedUserCars(userId);
    }
}
