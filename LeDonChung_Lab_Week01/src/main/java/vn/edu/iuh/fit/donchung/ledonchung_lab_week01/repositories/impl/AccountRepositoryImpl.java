package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.impl;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Log;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.AccountRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.utils.AppUtils;

import java.time.Instant;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Account findByAccountIdAndPassword(String accountId, String password) {
        try(var em = AppUtils.getEntityManager()) {
            List<Account> result = em.createNamedQuery("Account.findByAccountIdAndPassword", Account.class)
                    .setParameter("accountId", accountId)
                    .setParameter("password", password)
                    .getResultList();

            return result.isEmpty() ? null : result.get(0);
        }
    }

    @Override
    public List<Account> findAllAccountNotIsAdmin() {
        try(var em = AppUtils.getEntityManager()) {
            return em.createNamedQuery("Account.findAllAccountNotIsAdmin", Account.class)
                    .getResultList();
        }
    }

    @Override
    public Account findByAccountId(String accountId) {
        try(var em = AppUtils.getEntityManager()) {
            List<Account> result = em.createNamedQuery("Account.findByAccountId", Account.class)
                    .setParameter("accountId", accountId)
                    .getResultList();

            return result.isEmpty() ? null : result.get(0);
        }
    }

    @Override
    public Account save(Account account) {

        try(var em = AppUtils.getEntityManager()) {
            em.getTransaction().begin();
            if (account.getAccountId() == null) {
                String id = Instant.now().getEpochSecond() + "";
                account.setAccountId(id);

                em.persist(account);

            } else {
                String query = "UPDATE account SET  full_name = ?, email = ?, phone = ? WHERE account_id = ?";
                em.createNativeQuery(query)
                        .setParameter(1, account.getFullName())
                        .setParameter(2, account.getEmail())
                        .setParameter(3, account.getPhone())
                        .setParameter(4, account.getAccountId())
                        .executeUpdate();
            }


            em.getTransaction().commit();
            return account;
        }
    }


}
