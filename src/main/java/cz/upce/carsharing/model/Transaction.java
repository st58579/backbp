package cz.upce.carsharing.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

@Data
public class Transaction {

    private float amount;
    private LocalDate createdAt;
    private int idDestination;
    private int idSource;

    public static RowMapper<Transaction> getTransactionMapper(){
        return (rs, rowNum) -> {
            Transaction transaction = new Transaction();
            transaction.setAmount(rs.getFloat("AMOUNT"));
            transaction.setCreatedAt(rs.getDate("CREATED_AT").toLocalDate());
            transaction.setIdDestination(rs.getInt("ID_DESTINATION"));
            transaction.setIdSource(rs.getInt("ID_SOURCE"));
            return transaction;
        };
    }
}
