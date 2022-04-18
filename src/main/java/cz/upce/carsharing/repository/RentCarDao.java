package cz.upce.carsharing.repository;

import cz.upce.carsharing.model.dto.RentCarRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RentCarDao {

    private final JdbcTemplate jdbcTemplate;

    public void rentCar(RentCarRequest rentCarRequest) {
        String createLeaseDeal = "INSERT INTO LEASE_DEAL (ACTIVE, DATE_FROM, DATE_TO, CONDITION_RENTAL, CONDITION_RETURN, ID_CAR, ID_USER) " +
                "VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(createLeaseDeal, 1, rentCarRequest.getStartDate(), rentCarRequest.getEndDate(),
                "RENTAL", "RETURN", rentCarRequest.getCarId(), rentCarRequest.getUserId());
        String setCarNotActive = "UPDATE CAR SET AVAILABLE = 0 WHERE ID_CAR = ?";
        jdbcTemplate.update(setCarNotActive, rentCarRequest.getCarId());
    }

    public void returnCar(Integer id) {
        String returnCar = "UPDATE LEASE_DEAL SET ACTIVE = 0 WHERE ID_CAR = ?";
        jdbcTemplate.update(returnCar, id);
        String setAvailable = "UPDATE CAR SET AVAILABLE = 1 WHERE ID_CAR = ?";
        jdbcTemplate.update(setAvailable,id);
    }

}
