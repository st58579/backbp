package cz.upce.carsharing.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeRoleRequest {
    private String idAdmin;
    private String idUser;
    private String role;
}
