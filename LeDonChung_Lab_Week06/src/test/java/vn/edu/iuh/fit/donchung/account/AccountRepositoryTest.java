package vn.edu.iuh.fit.donchung.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.donchung.entities.Account;
import vn.edu.iuh.fit.donchung.repositories.AccountRepository;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void insertAll() {
        for (long i = 0; i < 100; i++) {
            Random random = new Random();
            Account account = Account.builder()
                    .accountName("Name #" + i)
                    .accountNumber(UUID.randomUUID().toString())
                    .balance(random.nextDouble(1000000))
                    .build();

            accountRepository.save(account);
        }

        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(System.out::println);
        assert accounts.size() == 100;
    }


}
