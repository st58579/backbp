package cz.upce.carsharing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String username;
    private String name;
    private String surname;
    private String role;

    public static RowMapper<UserDto> getUserDtoMapper() {
        return (rs, rowNum) -> {
            UserDto user = new UserDto();
            user.setId(rs.getInt("ID_USER"));
            user.setUsername(rs.getString("LOGIN"));
            user.setName(rs.getString("NAME"));
            user.setSurname(rs.getString("SURNAME"));
            user.setRole(rs.getString("ROLE"));
            return user;
        };
    }
}
