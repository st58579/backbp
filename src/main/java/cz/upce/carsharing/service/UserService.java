package cz.upce.carsharing.service;

import cz.upce.carsharing.dto.RegistrationUserRequest;
import cz.upce.carsharing.dto.UserDetailsDto;
import cz.upce.carsharing.dto.UserDto;
import cz.upce.carsharing.exceptions.ValidationException;
import cz.upce.carsharing.model.User;
import cz.upce.carsharing.model.Wallet;
import cz.upce.carsharing.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDto register(RegistrationUserRequest user) {
        if(userDao.checkIsUserExistByUsername(user.getUsername())){
            throw new ValidationException("Username already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addNewUser(user);
        return userDao.getUserByUsername(user.getUsername()).toUserDto();
    }

    public UserDto getUserById(Integer userId) {
        return userDao.getUserById(userId).toUserDto();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public ResponseEntity<UserDetailsDto> getUserDetailsByUsername(String username) {
        return userDao.getUserDetailsByUsername(username);
    }

    public void updateUserPicture(Integer id, String picture) {
        userDao.updateUserPicture(id, picture);
    }

    public void updateUserDetails(Integer id, UserDetailsDto userDetails) {
        userDao.updateUserDetails(id, userDetails);
    }
    public void addUserDetails(Integer id, UserDetailsDto userDetails) {
        userDao.addUserDetails(id, userDetails);
    }
    public Wallet getUserWallet(Integer id) {
        return userDao.getUserWallet(id);
    }
}
