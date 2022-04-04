package cz.upce.carsharing.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

@Data
public class Make {
    private int idMake;
    private String make;

    public static RowMapper<Make> getMakeMapper() {
        return (rs, rowNum) -> {
            Make role = new Make();
            role.setIdMake(rs.getInt("ID_MAKE"));
            role.setMake(rs.getString("MAKE"));
            return role;
        };
    }
}
