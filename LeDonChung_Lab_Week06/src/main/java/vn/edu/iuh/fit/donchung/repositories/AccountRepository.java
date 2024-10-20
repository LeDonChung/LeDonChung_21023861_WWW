package vn.edu.iuh.fit.donchung.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.donchung.entities.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Page<Account> findByBalanceGreaterThan(double balance, Pageable pageable);
}
