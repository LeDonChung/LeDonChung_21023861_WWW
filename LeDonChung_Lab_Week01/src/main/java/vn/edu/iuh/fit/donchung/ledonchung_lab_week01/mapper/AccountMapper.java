package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.mapper;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.GrantAccessDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccess;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
public class AccountMapper {
    @Inject
    private GrantAccessMapper grantAccessMapper;
    public AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setFullName(account.getFullName());
        accountDto.setEmail(account.getEmail());
        accountDto.setPassword(account.getPassword());
        accountDto.setPhone(account.getPhone());
        accountDto.setStatus(account.getStatus());
        Set<GrantAccessDto> grantAccesses = new HashSet<>();
        if(account.getGrantAccesses() != null) {
            grantAccesses = account.getGrantAccesses().stream().map(grantAccessMapper::toDto).collect(Collectors.toSet());
        }
        accountDto.setGrantAccesses(grantAccesses);
        return accountDto;
    }

    public Account toEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setFullName(accountDto.getFullName());
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());
        account.setPhone(accountDto.getPhone());
        account.setStatus(accountDto.getStatus());
        Set<GrantAccessDto> grantAccesses = accountDto.getGrantAccesses();
        account.setGrantAccesses(grantAccesses.stream().map(grant -> {
            GrantAccess grantTemp = grantAccessMapper.toEntity(grant);

            Account accountTemp = new Account();
            accountTemp.setAccountId(accountDto.getAccountId());

            Role roleTemp = new Role();
            roleTemp.setRoleId(grant.getRole().getRoleId());

            grantTemp.setRole(roleTemp);
            grantTemp.setAccount(accountTemp);
            return grantTemp;
        }).collect(Collectors.toSet()));
        return account;
    }
}
