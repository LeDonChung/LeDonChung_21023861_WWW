package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;

public interface AccountRepository {
    Account findByAccountIdAndPassword(String accountId, String password);
}
