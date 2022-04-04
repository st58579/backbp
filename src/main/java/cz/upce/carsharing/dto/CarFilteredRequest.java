package cz.upce.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarFilteredRequest {

    private Integer userId;
    private Integer makeId;
    private Integer typeId;
    private Integer page;
    private Integer limit;
}
