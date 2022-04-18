package cz.upce.carsharing.repository;

import cz.upce.carsharing.model.dto.ChangeRoleRequest;
import cz.upce.carsharing.model.dto.RegistrationUserRequest;
import cz.upce.carsharing.model.dto.UserDetailsDto;
import cz.upce.carsharing.exceptions.DaoException;
import cz.upce.carsharing.model.User;
import cz.upce.carsharing.model.Wallet;
import cz.upce.carsharing.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public List<UserDto> getAllUsers() {
        String query = "SELECT * FROM USER_DETAILS_VIEW ORDER BY ID_USER";
        List<UserDto> foundUsers = jdbcTemplate.query(query, UserDto.getUserDtoMapper());
        return foundUsers;
    }

    public User getUserById(Integer id) {
        String query = "SELECT * FROM APP_USER WHERE ID_USER = ?";
        List<User> foundUsers = jdbcTemplate.query(query, new Object[]{id}, User.getUserMapper());
        if (foundUsers.size() != 1) {
            throw new DaoException("User with ID " + id + " not found");
        }
        return foundUsers.get(0);
    }


    public User getUserByUsername(String username) {
        String query = "SELECT * FROM APP_USER WHERE LOGIN like ?";
        List<User> foundUsers = jdbcTemplate.query(query, new Object[]{username}, User.getUserMapper());
        if (foundUsers.size() != 1) {
            throw new DaoException("User with username " + username + " not found");
        }
        return foundUsers.get(0);
    }

    public ResponseEntity<UserDetailsDto> getUserDetailsByUsername(String username) {
        String query = "SELECT * FROM USER_DETAILS_VIEW WHERE LOGIN like ?";
        List<UserDetailsDto> foundUsers = jdbcTemplate.query(query, new Object[]{username}, UserDetailsDto.getMapper());
        if (foundUsers.size() != 1) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundUsers.get(0), HttpStatus.OK);
    }

    public boolean checkIsUserExistByUsername(String username) {
        String query = "SELECT * FROM APP_USER WHERE LOGIN like ?";
        List<User> foundUsers = jdbcTemplate.query(query, new Object[]{username}, User.getUserMapper());
        return foundUsers.size() > 0;
    }

    public void addNewUser(RegistrationUserRequest user) {
        String query = "INSERT INTO APP_USER (LOGIN, PASSWORD, ID_ROLE) VALUES (?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRole().equals("admin") ? 1 : 2);
            return ps;
        });
    }

    public void updateUserPicture(Integer id, String picture) {
        String query = "UPDATE CONTACT_DETAILS SET IMG = ? WHERE ID_USER = ?";
        jdbcTemplate.update(query, picture, id);
    }

    public void updateUserDetails(Integer id, UserDetailsDto userDetails) {
        String query = "UPDATE CONTACT_DETAILS SET NAME = ?, SURNAME = ?, EMAIL = ?, CITY = ?," +
                " PHONE_NUMBER = ?, DOCUMENT_NUMBER = ?, IMG = ? WHERE ID_USER = ?";
        jdbcTemplate.update(query, userDetails.getName(), userDetails.getSurname(), userDetails.getEmail(),
                userDetails.getCity(), userDetails.getPhoneNumber(), userDetails.getDocumentNumber(), userDetails.getImage(), id);
    }
    public void addUserDetails(Integer id, UserDetailsDto userDetails) {
        String query = "insert into contact_details(name, surname, email, city, phone_number, document_number, id_user, img)" +
                "values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userDetails.getName());
            ps.setString(2, userDetails.getSurname());
            ps.setString(3, userDetails.getEmail());
            ps.setString(4, userDetails.getCity());
            ps.setString(5, userDetails.getPhoneNumber());
            ps.setString(6, userDetails.getDocumentNumber());
            ps.setInt(7, id);
            ps.setString(8,userDetails.getImage());
            return ps;
        });
    }
    public Wallet getUserWallet(Integer id) {
        String query = "SELECT * FROM WALLET WHERE ID_USER = ?";
        List<Wallet> foundUsers = jdbcTemplate.query(query, new Object[]{id}, Wallet.getWalletMapper());
        if (foundUsers.size() != 1) {
            throw new DaoException("Wallet with ID " + id + " not found");
        }
        return foundUsers.get(0);
    }

    public List<UserDto> changeUserRole(ChangeRoleRequest request) {
        String query = "UPDATE APP_USER SET ID_ROLE = ? WHERE ID_USER = ?";
        jdbcTemplate.update(query, request.getRole().equals("admin") ? 1 : 2, request.getIdUser());
        return getAllUsers();
    }
}
