package cz.upce.carsharing.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Clob;

@Data
@NoArgsConstructor
public class Car {
    private Integer idCar;
    private String model;
    private Integer year;
    private Integer seatsNumber;
    private Integer pricePerDay;
    private String makeName;
    private String type;
    private Integer available;
    private String img;
    private Integer idUser;

    public static RowMapper<Car> getCarMapper(){
        return (rs, rowNum) -> {
            Car car = new Car();
            car.setIdCar(rs.getInt("ID_CAR"));
            car.setModel(rs.getString("MODEL"));
            car.setYear(rs.getInt("YEAR"));
            car.setSeatsNumber(rs.getInt("SEATS_NUMBER"));
            car.setPricePerDay(rs.getInt("PRICE_PER_DAY"));
            car.setIdUser(rs.getInt("ID_USER"));
            car.setType(rs.getString("TYPE"));
            car.setAvailable(rs.getInt("AVAILABLE"));
            car.setMakeName(rs.getString("MAKE"));
            car.setIdUser(rs.getInt("ID_USER"));
            Clob image = rs.getClob("IMG");
            if (image != null){
                car.setImg(image.getSubString(1, (int) image.length()));
            }
            return car;
        };
    }
}
