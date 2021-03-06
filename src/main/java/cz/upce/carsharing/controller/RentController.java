package cz.upce.carsharing.controller;

import cz.upce.carsharing.model.dto.RentCarRequest;
import cz.upce.carsharing.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rent")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PostMapping
    public void rentAuto(@RequestBody RentCarRequest rentCarRequest){
        rentService.rentCar(rentCarRequest);
    }

    @PostMapping(value = "/return/{id}")
    public void returnAuto(@PathVariable(value = "id") Integer id) { rentService.returnCar(id); }

}
