package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.impl;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.AccountRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.utils.AppUtils;

public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Account findByAccountIdAndPassword(String accountId, String password) {
        try(var em = AppUtils.getEntityManager()) {
            return em.createNamedQuery("Account.findByAccountIdAndPassword", Account.class)
                    .setParameter("accountId", accountId)
                    .setParameter("password", password)
                    .getSingleResult();
        }
    }
}
