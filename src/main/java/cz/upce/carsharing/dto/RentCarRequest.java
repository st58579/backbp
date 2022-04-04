package cz.upce.carsharing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RentCarRequest {
    private Integer userId;
    private Integer carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
