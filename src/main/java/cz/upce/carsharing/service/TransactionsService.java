package cz.upce.carsharing.service;

import cz.upce.carsharing.model.UserRentHistory;
import cz.upce.carsharing.model.UserTransactions;
import cz.upce.carsharing.model.dto.CarResponse;
import cz.upce.carsharing.repository.TransactionsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final TransactionsDao transactionsDao;

    public List<UserTransactions> getAllUserTransactionsById(Integer id) {
        return transactionsDao.getAllUserTransactionsById(id);
    }

    public List<UserTransactions> getAllTransactions() {
        return transactionsDao.getAllTransactions();
    }

    public List<UserRentHistory> getUserRentHistory(Integer id) {
        return transactionsDao.getUserRentHistory(id);
    }
}
