package cz.upce.carsharing.controller;

import cz.upce.carsharing.model.User;
import cz.upce.carsharing.model.dto.ChangeRoleRequest;
import cz.upce.carsharing.model.dto.UserDetailsDto;
import cz.upce.carsharing.model.Wallet;
import cz.upce.carsharing.model.dto.UserDto;
import cz.upce.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDetailsDto> getUserDetails(@PathVariable("username") String username){
        return userService.getUserDetailsByUsername(username);
    }
    @GetMapping(value = "/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
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

    @PostMapping(value = "/admin/changeRole")
    public List<UserDto> changeUserRole(@RequestBody ChangeRoleRequest request){
        return userService.changeUserRole(request);
    }

}
