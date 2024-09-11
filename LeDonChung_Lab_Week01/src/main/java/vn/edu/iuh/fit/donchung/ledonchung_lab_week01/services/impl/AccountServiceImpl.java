package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.GrantAccessDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.RoleDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccess;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.GrantAccessId;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.mapper.AccountMapper;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.mapper.GrantAccessMapper;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.AccountRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.LogRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.AccountService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {
    @Inject
    private AccountRepository accountRepository;
    @Inject
    private AccountMapper accountMapper;
    @Inject
    private GrantAccessRepository grantAccessRepository;
    @Inject
    private LogRepository logRepository;
    @Inject
    private GrantAccessMapper grantAccessMapper;
    @Override
    public AccountDto login(AccountDto accountDto) {
        Account account = accountRepository.findByAccountIdAndPassword(accountDto.getAccountId(), accountDto.getPassword());

        if (account != null) {
            logRepository.saveTimeLogin(account.getAccountId());
            return accountMapper.toDto(account);
        }
        return null;
    }

    @Override
    public void logout(AccountDto account) {
        logRepository.saveTimeLogout(account.getAccountId());
    }

    @Override
    public List<AccountDto> getAllAccount() {
        return accountRepository.findAllAccountNotIsAdmin().stream()
                .map(account -> accountMapper.toDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(String accountId) {
        return accountMapper.toDto(accountRepository.findByAccountId(accountId));
    }

    @Override
    public AccountDto save(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        String id = account.getAccountId();
        if(account.getAccountId() != null) {

            accountDto.getGrantAccesses().forEach(g -> {
                GrantAccess grantAccess = grantAccessMapper.toEntity(g);
                GrantAccessId grantAccessId = new GrantAccessId();
                grantAccessId.setAccountId(id);
                grantAccessId.setRoleId(g.getRole().getRoleId());
                grantAccess.setId(grantAccessId);

                Account accountTemp = new Account();
                accountTemp.setAccountId(id);

                grantAccess.setAccount(accountTemp);
                Role role = new Role();
                role.setRoleId(g.getRole().getRoleId());
                grantAccess.setRole(role);

                grantAccessRepository.save(grantAccess);
            });
        }
        return accountMapper.toDto(account);
    }

}
