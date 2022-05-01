package cz.upce.carsharing.model;

import lombok.Data;

@Data
public class UserDetails {
    private String name;
    private String surname;
    private String email;
    private String city;
    private String phoneNumber;
    private String documentNumber;
}
