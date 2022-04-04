package cz.upce.carsharing.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

@Data
public class LeaseDeal {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String conditionRental;
    private String conditionReturn;

    public static RowMapper<LeaseDeal> getLeaseDealMapper(){
        return (rs, rowNum) -> {
            LeaseDeal leaseDeal = new LeaseDeal();
            leaseDeal.setDateFrom(rs.getDate("DATE_FROM").toLocalDate());
            leaseDeal.setDateTo(rs.getDate("DATE_TO").toLocalDate());
            leaseDeal.setConditionRental(rs.getString("CONDITION_RENTAL"));
            leaseDeal.setConditionReturn(rs.getString("CONDITION_RETURN"));
            return leaseDeal;
        };
    }
}
