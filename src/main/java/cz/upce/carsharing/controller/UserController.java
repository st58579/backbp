package cz.upce.carsharing.controller;

import cz.upce.carsharing.dto.UserDetailsDto;
import cz.upce.carsharing.model.Wallet;
import cz.upce.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDetailsDto> getUserDetails(@PathVariable("username") String username){
        return userService.getUserDetailsByUsername(username);
    }

    @PostMapping(value = "/update/{id}")
    public void updateUserDetails(@PathVariable(value = "id") Integer id, @RequestBody UserDetailsDto userDetails){
        userService.updateUserDetails(id, userDetails);
    }

    @PostMapping(value = "/add/{id}")
    public void addUserDetails(@PathVariable(value = "id") Integer id, @RequestBody UserDetailsDto userDetails){
        userService.addUserDetails(id, userDetails);
    }
    @PostMapping(value = "/img/{id}")
    public void updateUserPicture(@PathVariable("id") Integer id, String picture){
        userService.updateUserPicture(id, picture);
    }

    @GetMapping(value = "/wallet/{id}")
    public Wallet getUserWallet(@PathVariable(value = "id") Integer id){
        return userService.getUserWallet(id);
    }


}
