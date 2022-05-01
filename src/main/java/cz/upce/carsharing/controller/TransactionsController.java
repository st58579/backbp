package cz.upce.carsharing.controller;

import cz.upce.carsharing.model.UserRentHistory;
import cz.upce.carsharing.model.UserTransactions;
import cz.upce.carsharing.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @GetMapping("/user/{id}")
    public List<UserTransactions> getAllUserTransactionsById(@PathVariable(value = "id") Integer id) {
        return transactionsService.getAllUserTransactionsById(id);
    }

    @GetMapping("/all")
    public List<UserTransactions> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    @GetMapping(value = "/history/user/{id}")
    public List<UserRentHistory> getUserRentHistory(@PathVariable(value = "id") Integer id) { return transactionsService.getUserRentHistory(id); }
}
