package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;

import java.util.List;

public interface AccountService {
    AccountDto login(AccountDto accountDto);

    void logout(AccountDto account);

    List<AccountDto> getAllAccount();

    AccountDto getById(String accountId);

    AccountDto save(AccountDto accountDto);
}
