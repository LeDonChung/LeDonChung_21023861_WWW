package vn.edu.iuh.fit.donchung.services;

import vn.edu.iuh.fit.donchung.dto.AccountDto;
import vn.edu.iuh.fit.donchung.dto.PageDTO;

import java.util.List;

public interface AccountService {
    public List<AccountDto> getAll();
    public PageDTO<AccountDto> getByPage(int pageNum, int pageSize);
    public PageDTO<AccountDto> getByBalance(double balance, int pageNum, int pageSize);
}
