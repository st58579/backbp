package cz.upce.carsharing.model;

import lombok.Data;

@Data
public class ContactDetails {

    private int idContact;
    private String name;
    private String surname;
    private String email;
    private String country;
    private String city;
    private String phoneNumber;
//    private Image image;
}
