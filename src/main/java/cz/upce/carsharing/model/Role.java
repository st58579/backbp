package cz.upce.carsharing.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

@Data
public class Role {
    private int idRole;
    private String role;

    public static RowMapper<Role> getRoleMapper() {
        return (rs, rowNum) -> {
            Role role = new Role();
            role.setIdRole(rs.getInt("ID_ROLE"));
            role.setRole(rs.getString("ROLE"));
            return role;
        };
    }
}
