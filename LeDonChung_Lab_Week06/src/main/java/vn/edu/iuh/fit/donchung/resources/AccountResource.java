package vn.edu.iuh.fit.donchung.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.donchung.dto.AccountDto;
import vn.edu.iuh.fit.donchung.dto.PageDTO;
import vn.edu.iuh.fit.donchung.services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountResource {
    @Autowired
    private AccountService accountService;
    @GetMapping("")
    public ResponseEntity<Object> getAll(@RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if(pageNum != null) {
            return new ResponseEntity<>(
                    accountService.getByPage(pageNum, pageSize),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                accountService.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/findByBalance")
    public ResponseEntity<Object> getBalance(@RequestParam(value = "balance", required = true) double balance, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
            return new ResponseEntity<>(
                    accountService.getByBalance(balance, pageNum, pageSize),
                    HttpStatus.OK
            );
    }
}
