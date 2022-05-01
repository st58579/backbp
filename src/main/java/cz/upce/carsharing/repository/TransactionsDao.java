package cz.upce.carsharing.repository;

import cz.upce.carsharing.model.UserRentHistory;
import cz.upce.carsharing.model.UserTransactions;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionsDao {

    private final JdbcTemplate jdbcTemplate;

    public List<UserTransactions> getAllUserTransactionsById(Integer id) {
        String query = "SELECT * FROM USER_TRANSACTION WHERE ID_USER = ?";
        return jdbcTemplate.query(query, new Object[]{id}, UserTransactions.getUserTransactionsMapper());
    }

    public List<UserTransactions> getAllTransactions() {
        String query = "SELECT * FROM USER_TRANSACTION ORDER BY ID_TRANSACTION";
        return jdbcTemplate.query(query, new Object[]{}, UserTransactions.getUserTransactionsMapper());
    }

    public List<UserRentHistory> getUserRentHistory(Integer id) {
        String query = "SELECT * FROM RENT_HISTORY WHERE ID_USER = ? ORDER BY ACTIVE DESC";
        return jdbcTemplate.query(query, new Object[]{id}, UserRentHistory.getUserRentHistoryMapper());
    }
}
