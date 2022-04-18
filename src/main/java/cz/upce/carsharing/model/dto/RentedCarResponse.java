package cz.upce.carsharing.model.dto;

import cz.upce.carsharing.model.RentedCar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class RentedCarResponse {
    private List<RentedCar> cars;
    private Integer count;
}
