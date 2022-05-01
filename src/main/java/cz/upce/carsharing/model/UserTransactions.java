package cz.upce.carsharing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactions {
    private Integer idUser;
    private String login;
    private Integer idTransaction;
    private Float amount;
    private LocalDate creationDate;

    public static RowMapper<UserTransactions> getUserTransactionsMapper() {
        return (rs, rowNum) -> {
            UserTransactions transactions = new UserTransactions();
            transactions.setIdUser(rs.getInt("ID_USER"));
            transactions.setLogin(rs.getString("LOGIN"));
            transactions.setIdTransaction(rs.getInt("ID_TRANSACTION"));
            transactions.setAmount(rs.getFloat("AMOUNT"));
            transactions.setCreationDate(rs.getDate("CREATED_AT").toLocalDate());
            return transactions;
        };
    }
}
