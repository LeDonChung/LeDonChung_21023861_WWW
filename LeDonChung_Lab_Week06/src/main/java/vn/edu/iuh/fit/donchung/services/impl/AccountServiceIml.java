package vn.edu.iuh.fit.donchung.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.donchung.dto.AccountDto;
import vn.edu.iuh.fit.donchung.dto.PageDTO;
import vn.edu.iuh.fit.donchung.entities.Account;
import vn.edu.iuh.fit.donchung.mapper.AccountMapper;
import vn.edu.iuh.fit.donchung.repositories.AccountRepository;
import vn.edu.iuh.fit.donchung.services.AccountService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceIml implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::toDto).toList();
    }

    @Override
    public PageDTO<AccountDto> getByPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Account> pageAccount = accountRepository.findAll(pageable);
        List<AccountDto> accountDtos = pageAccount.getContent().stream().map(accountMapper::toDto).toList();

        PageDTO<AccountDto> pageDTO = new PageDTO<>();
        pageDTO.setPageNum(pageNum);
        pageDTO.setPageSize(pageSize);
        pageDTO.setContent(accountDtos);
        return pageDTO;
    }

    @Override
    public PageDTO<AccountDto> getByBalance(double balance, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize).withSort(Sort.by("balance").descending());
        Page<Account> pageAccount = accountRepository.findByBalanceGreaterThan(balance, pageable);
        List<AccountDto> accountDtos = pageAccount.getContent().stream().map(accountMapper::toDto).toList();

        PageDTO<AccountDto> pageDTO = new PageDTO<>();
        pageDTO.setPageNum(pageNum);
        pageDTO.setPageSize(pageSize);
        pageDTO.setContent(accountDtos);
        return pageDTO;
    }
}
