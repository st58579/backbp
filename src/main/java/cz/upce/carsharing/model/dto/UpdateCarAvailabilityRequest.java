package cz.upce.carsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UpdateCarAvailabilityRequest {
    private Integer availability;
    private Integer carId;
}
