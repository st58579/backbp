package cz.upce.carsharing.service;

import cz.upce.carsharing.model.dto.RentCarRequest;
import cz.upce.carsharing.exceptions.RentCarException;
import cz.upce.carsharing.model.Car;
import cz.upce.carsharing.model.Wallet;
import cz.upce.carsharing.repository.CarsharingDao;
import cz.upce.carsharing.repository.RentCarDao;
import cz.upce.carsharing.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class RentService {

    private final CarsharingDao carsharingDao;
    private final UserDao userDao;
    private final RentCarDao rentCarDao;

    @Transactional
    public void rentCar(RentCarRequest request) {
        if(request.getEndDate().isBefore(request.getStartDate())) {
            throw new RentCarException("Reservation end day cant be before start day!");
        }

        long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        Car car = carsharingDao.getCarById(request.getCarId());

        Wallet initiatorWallet = userDao.getUserWallet(request.getUserId());
        Wallet recipientWallet = userDao.getUserWallet(car.getIdUser());

        if(initiatorWallet.getBalance() < days * car.getPricePerDay()){
            throw new RentCarException("Insufficient amount on balance!");
        }

        carsharingDao.payToUser((int) (days * car.getPricePerDay()), initiatorWallet, recipientWallet);
        rentCarDao.rentCar(request);
    }

    public void returnCar(Integer id) {
        rentCarDao.returnCar(id);
    }
}
