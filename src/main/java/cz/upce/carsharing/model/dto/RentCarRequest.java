package cz.upce.carsharing.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RentCarRequest {
    private Integer userId;
    private Integer carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
