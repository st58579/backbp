package cz.upce.carsharing.repository;

import cz.upce.carsharing.model.*;
import cz.upce.carsharing.model.dto.*;
import cz.upce.carsharing.exceptions.DaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarsharingDao {

    private final JdbcTemplate jdbcTemplate;

    public Car getCarById(Integer carId) {
        String query = "SELECT * FROM AUTO_TYPE WHERE ID_CAR = ?";
        List<Car> foundCars = jdbcTemplate.query(query, new Object[]{carId}, Car.getCarMapper());
        if (foundCars.size() != 1) {
            throw new DaoException("Car with ID " + carId + " not found");
        }
        return foundCars.get(0);
    }

    public CarResponse getUserCars(Integer userId) {
        String query = "SELECT * FROM AUTO_TYPE WHERE ID_USER = ? ORDER BY ID_CAR DESC";
        List<Car> foundCars = jdbcTemplate.query(query, new Object[]{userId}, Car.getCarMapper());
        return new CarResponse(foundCars, foundCars.size());
    }


    public List<Car> getAllCars(Integer userId) {
        String query = "SELECT * FROM AUTO_TYPE WHERE ID_USER != ? AND AVAILABLE = 1 ORDER BY ID_CAR DESC";
        List<Car> cars = jdbcTemplate.query(query, new Object[]{userId}, Car.getCarMapper());
        return cars;
    }

    public CarResponse getCarsPaginated(int offset, int limit, int idUser) {
        int count = getUserCars(idUser).getCount();

        String query = "SELECT * FROM AUTO_TYPE " +
                "WHERE ID_USER != ? AND AVAILABLE = 1 " +
                "ORDER BY ID_CAR DESC " +
                "OFFSET ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";
        List<Car> foundCars = jdbcTemplate.query(query, new Object[]{idUser, offset, limit}, Car.getCarMapper());
        return new CarResponse(foundCars, count);
    }

    public CarResponse getCarsPaginatedAndFilteredByMake(int offset, int limit, int makeId, int idUser) {
        String query = "SELECT * FROM AUTO_TYPE " +
                "WHERE ID_MAKE = ? " +
                "AND ID_USER != ? " +
                "AND AVAILABLE = 1 " +
                "ORDER BY ID_CAR " +
                "OFFSET ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";
        List<Car> foundCars = jdbcTemplate.query(query, new Object[]{makeId, idUser, offset, limit}, Car.getCarMapper());
        return new CarResponse(foundCars, foundCars.size());
    }


    public CarResponse getCarsPaginatedAndFilteredByType(int offset, int limit, int typeId, int idUser) {
        String query = "SELECT * FROM AUTO_TYPE " +
                "WHERE ID_TYPE = ? " +
                "AND ID_USER != ? " +
                "AND AVAILABLE = 1 " +
                "ORDER BY ID_CAR " +
                "OFFSET ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";
        List<Car> foundCars = jdbcTemplate.query(query, new Object[]{typeId, idUser, offset, limit}, Car.getCarMapper());
        return new CarResponse(foundCars, foundCars.size());
    }

    public CarResponse getCarsPaginatedAndFilteredByMakeAndType(CarFilteredRequest request) {
        String query = "SELECT * FROM AUTO_TYPE " +
                "WHERE ID_MAKE = ? " +
                "AND ID_TYPE = ? " +
                "AND ID_USER != ? " +
                "AND AVAILABLE = 1 " +
                "ORDER BY ID_CAR " +
                "OFFSET ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";
        List<Car> foundCars = jdbcTemplate.query(query, new Object[]{request.getMakeId(), request.getTypeId(), request.getUserId(), request.getPage(), request.getLimit()}, Car.getCarMapper());
        return new CarResponse(foundCars, foundCars.size());
    }

    public void addCar(CarDto car) {
        String query = "INSERT INTO CAR " +
                "(MODEL, YEAR, SEATS_NUMBER, PRICE_PER_DAY, ID_USER, ID_TYPE, ID_MAKE, IMG, AVAILABLE, TRANSMISSION_TYPE, ENGINE)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(
                query,
                car.getModel(),
                car.getYear(),
                car.getSeatsNumber(),
                car.getPricePerDay(),
                car.getIdUser(),
                car.getIdType(),
                car.getIdMake(),
                car.getImg(),
                1,
                car.getTransmission(),
                car.getEngine()
        );
    }

    @Transactional
    public void payToUser(Integer amount, Wallet initiatorWallet, Wallet recipientWallet) {
        String paymentTransaction = "INSERT INTO TRANSACTION (AMOUNT, CREATED_AT, ID_WALLET, ID_DESTINATION, ID_SOURCE) " +
                "VALUES (?,?,?,?,?)";
        jdbcTemplate.update(paymentTransaction, -amount, LocalDate.now(), initiatorWallet.getIdWallet(), recipientWallet.getIdWallet(), initiatorWallet.getIdWallet());
        jdbcTemplate.update(paymentTransaction, amount, LocalDate.now(), recipientWallet.getIdWallet(), recipientWallet.getIdWallet(), initiatorWallet.getIdWallet());
        String paymentUpdate = "UPDATE WALLET SET BALANCE = ? WHERE ID_WALLET = ?";
        jdbcTemplate.update(paymentUpdate, initiatorWallet.getBalance() - amount, initiatorWallet.getIdWallet());
        jdbcTemplate.update(paymentUpdate, recipientWallet.getBalance() + amount, recipientWallet.getIdWallet());
    }

    public void updateCarPrice(Integer carId, Integer price) {
        String query = "UPDATE CAR SET PRICE_PER_DAY = ? WHERE ID_CAR = ?";
        jdbcTemplate.update(query, price, carId);
    }

    public List<Make> getMakes() {
        String query = "SELECT * FROM MAKE";
        return jdbcTemplate.query(query, Make.getMakeMapper());
    }

    public List<Type> getTypes() {
        String query = "SELECT * FROM TYPE";
        return jdbcTemplate.query(query, Type.getTypeMapper());
    }

    public RentedCarResponse getRentedUserCars(Integer userId) {
        String query = "SELECT * FROM RENTED_AUTO WHERE USER_RENT = ? AND ACTIVE = 1";
        List<RentedCar> rentedCars = jdbcTemplate.query(query,new Object[]{userId}, RentedCar.getRentedCarMapper());
        return new RentedCarResponse(rentedCars, rentedCars.size());
    }

    public void addType(AddTypeRequest type) {
        String query = "INSERT INTO TYPE (type) VALUES (?)";
        jdbcTemplate.update(query, type.getType());
    }

    public void addMake(AddMakeRequest make) {
        String query = "INSERT INTO MAKE (make) VALUES (?)";
        jdbcTemplate.update(query, make.getMake());
    }

    public void setCarAvailable(Integer carId) {
        String query = "UPDATE CAR SET AVAILABLE = 1 WHERE ID_CAR = ?";
        jdbcTemplate.update(query,carId);
    }
    public void setCarUnavailable(Integer carId) {
        String query = "UPDATE CAR SET AVAILABLE = 0 WHERE ID_CAR = ?";
        jdbcTemplate.update(query,carId);
    }
}
