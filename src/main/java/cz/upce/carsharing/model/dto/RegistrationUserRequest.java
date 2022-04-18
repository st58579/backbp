package cz.upce.carsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationUserRequest {
    private String username;
    private String password;
    private String role;
}
