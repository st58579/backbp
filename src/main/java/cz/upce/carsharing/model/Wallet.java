package cz.upce.carsharing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Wallet {
    private Integer idWallet;
    private Float balance;

    public static RowMapper<Wallet> getWalletMapper() {
        return  (rs, rowNum) -> {
            Wallet wallet = new Wallet();
            wallet.setIdWallet(rs.getInt("ID_WALLET"));
            wallet.setBalance(rs.getFloat("BALANCE"));
            return wallet;
        };
    }
}
