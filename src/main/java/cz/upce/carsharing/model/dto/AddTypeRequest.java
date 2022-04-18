package cz.upce.carsharing.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddTypeRequest {
    private String type;
}
