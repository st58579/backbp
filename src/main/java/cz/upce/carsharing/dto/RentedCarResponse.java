package cz.upce.carsharing.dto;

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
