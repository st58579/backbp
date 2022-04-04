package cz.upce.carsharing.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

@Data
public class Type {
    private int idType;
    private String type;

    public static RowMapper<Type> getTypeMapper() {
        return (rs, rowNum) -> {
            Type type = new Type();
            type.setIdType(rs.getInt("ID_TYPE"));
            type.setType(rs.getString("TYPE"));
            return type;
        };
    }
}
