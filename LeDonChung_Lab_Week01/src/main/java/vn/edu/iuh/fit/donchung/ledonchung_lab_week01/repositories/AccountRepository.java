package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;

import java.util.List;

public interface AccountRepository {
    Account findByAccountIdAndPassword(String accountId, String password);

    List<Account> findAllAccountNotIsAdmin();

    Account findByAccountId(String accountId);

    Account save(Account account);
}
