package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.GrantAccessDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.RoleDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.AccountRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.AccountService;

import java.util.Set;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {
    @Inject
    private AccountRepository accountRepository;
    @Override
    public AccountDto find(AccountDto accountDto) {
        Account account = accountRepository.findByAccountIdAndPassword(accountDto.getAccountId(), accountDto.getPassword());
        if (account != null) {
            return toDto(account);
        }
        return null;
    }

    public AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setPassword(account.getPassword());
        accountDto.setFullName(account.getFullName());
        accountDto.setEmail(account.getEmail());
        accountDto.setPhone(account.getPhone());

        Set<GrantAccessDto> grantAccesses =
                account.getGrantAccesses().stream().map(grantAccess -> {
                    GrantAccessDto grantAccessDto = new GrantAccessDto();
                    grantAccessDto.setRole(
                            new RoleDto(
                                    grantAccess.getRole().getRoleId(),
                                    grantAccess.getRole().getRoleName(),
                                    grantAccess.getRole().getDescription(),
                                    grantAccess.getRole().getStatus(),
                                    null
                            )
                    );
                    grantAccessDto.setIsGrant(grantAccess.getIsGrant());
                    grantAccessDto.setNote(grantAccess.getNote());
                    return grantAccessDto;
                }).collect(Collectors.toSet());

        accountDto.setGrantAccesses(grantAccesses);
        return accountDto;
    }
}
