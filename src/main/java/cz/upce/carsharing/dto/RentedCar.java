package cz.upce.carsharing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Clob;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RentedCar {
    private Integer idCar;
    private Integer idUserOwner;
    private Integer idUserRent;
    private String model;
    private Integer year;
    private Integer seatsNumber;
    private Integer pricePerDay;
    private String makeName;
    private String type;
    private String img;
    private Integer available;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String conditionRental;
    private String conditionReturn;

    public static RowMapper<RentedCar> getRentedCarMapper(){
        return (rs, rowNum) -> {
            RentedCar car = new RentedCar();
            car.setIdCar(rs.getInt("ID_CAR"));
            car.setIdUserOwner(rs.getInt("USER_OWNER"));
            car.setIdUserRent(rs.getInt("USER_RENT"));
            car.setModel(rs.getString("MODEL"));
            car.setYear(rs.getInt("YEAR"));
            car.setSeatsNumber(rs.getInt("SEATS_NUMBER"));
            car.setPricePerDay(rs.getInt("PRICE_PER_DAY"));
            car.setMakeName(rs.getString("MAKE"));
            car.setType(rs.getString("TYPE"));
            Clob image = rs.getClob("IMG");
            if (image != null){
                car.setImg(image.getSubString(1, (int) image.length()));
            }
            car.setAvailable(rs.getInt("AVAILABLE"));
            car.setDateFrom(rs.getDate("DATE_FROM").toLocalDate());
            car.setDateTo(rs.getDate("DATE_TO").toLocalDate());
            car.setConditionRental(rs.getString("CONDITION_RENTAL"));
            car.setConditionReturn(rs.getString("CONDITION_RETURN"));
            return car;
        };
    }
}
