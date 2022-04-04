package cz.upce.carsharing.dto;

import lombok.Data;

@Data
public class CarDto {
    private String model;
    private Integer year;
    private Integer seatsNumber;
    private Integer pricePerDay;
    private Integer idMake;
    private Integer idType;
    private String img;
    private Integer idUser;
}
