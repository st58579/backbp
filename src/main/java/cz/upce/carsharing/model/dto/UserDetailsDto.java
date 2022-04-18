package cz.upce.carsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsDto {
    private String username;
    private String role;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String phoneNumber;
    private String documentNumber;
    private String image;

    public UserDetailsDto(String username){
        this.username = username;
    }

    public static RowMapper<UserDetailsDto> getMapper() {
        return (rs, rowNum) -> {
            UserDetailsDto user = new UserDetailsDto();
            user.setRole(rs.getString("ROLE"));
            user.setUsername(rs.getString("LOGIN"));
            user.setName(rs.getString("NAME"));
            user.setSurname(rs.getString("SURNAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setCity(rs.getString("CITY"));
            user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            user.setDocumentNumber(rs.getString("DOCUMENT_NUMBER"));
            user.setImage(rs.getString("IMG"));
            return user;
        };
    }

}
