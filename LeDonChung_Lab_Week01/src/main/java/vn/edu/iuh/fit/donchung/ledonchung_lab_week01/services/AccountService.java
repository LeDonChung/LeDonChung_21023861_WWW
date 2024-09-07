package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;

public interface AccountService {
    AccountDto find(AccountDto accountDto);
}
