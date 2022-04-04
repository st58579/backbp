package cz.upce.carsharing.dto;

import cz.upce.carsharing.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarResponse {

    private List<Car> cars;
    private Integer count;
}
