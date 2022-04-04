package cz.upce.carsharing.repository;

import cz.upce.carsharing.dto.RentCarRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RentCarDao {

    private final JdbcTemplate jdbcTemplate;

    public void rentCar(RentCarRequest rentCarRequest) {
        String query = "INSERT INTO LEASE_DEAL (DATE_FROM, DATE_TO, CONDITION_RENTAL, CONDITION_RETURN, ID_CAR, ID_USER) " +
                "VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(query, rentCarRequest.getStartDate(), rentCarRequest.getEndDate(),
                "RENTAL", "RETURN", rentCarRequest.getCarId(), rentCarRequest.getUserId());
    }
}
