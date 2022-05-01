package cz.upce.carsharing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserRentHistory {
    private Integer idUser;
    private String login;
    private String model;
    private String make;
    private String type;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer active;

    public static RowMapper<UserRentHistory> getUserRentHistoryMapper() {
        return (rs, rowNum) -> {
            UserRentHistory history = new UserRentHistory();
            history.setIdUser(rs.getInt("ID_USER"));
            history.setLogin(rs.getString("LOGIN"));
            history.setModel(rs.getString("MODEL"));
            history.setMake(rs.getString("MAKE"));
            history.setType(rs.getString("TYPE"));
            history.setDateFrom(rs.getDate("DATE_FROM").toLocalDate());
            history.setDateTo(rs.getDate("DATE_TO").toLocalDate());
            history.setActive(rs.getInt("ACTIVE"));
            return history;
        };
    }
}
