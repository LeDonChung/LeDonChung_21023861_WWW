package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.impl;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Log;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.LogRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.utils.AppUtils;

import java.time.Instant;
import java.util.List;

public class LogRepositoryImpl implements LogRepository {
    @Override
    public void saveTimeLogin(String accountId) {
        try (var em = AppUtils.getEntityManager()) {
            em.getTransaction().begin();

            // lấy log cuối cùng của tài khoản
            var query1 = "SELECT l FROM Log l WHERE l.accountId = :accountId ORDER BY l.id DESC LIMIT 1";
            List<Log> logs = em.createQuery(query1, Log.class)
                    .setParameter("accountId", accountId)
                    .getResultList();

            Log log = logs.isEmpty() ? null : logs.get(0);

            if(log == null) {
                // tạo một log mới
                Log newLog = new Log();
                newLog.setAccountId(accountId);
                newLog.setLoginTime(Instant.now());
                newLog.setLogoutTime(null);
                newLog.setNotes("");
                em.persist(newLog);
                em.getTransaction().commit();
                return;
            }
            // nếu log cuối cùng không có thời gian logout thì cập nhật lại thời gian login
            if (log.getLogoutTime() == null) {
                // cập nhật lại thời gian login
                var query2 = "UPDATE Log l SET l.loginTime = CURRENT_TIMESTAMP WHERE l.id = :logId";
                em.createQuery(query2)
                        .setParameter("logId", log.getId())
                        .executeUpdate();
            } else {
                // nếu đã logout thì tạo một log mới
                Log newLog = new Log();
                newLog.setAccountId(accountId);
                newLog.setLoginTime(Instant.now());
                newLog.setLogoutTime(null);
                newLog.setNotes("");
                em.persist(newLog);
            }
            em.getTransaction().commit();
        }
    }

    @Override
    public void saveTimeLogout(String accountId) {
        try (var em = AppUtils.getEntityManager()) {
            em.getTransaction().begin();

            // lấy log cuối cùng của tài khoản
            var query1 = "SELECT l FROM Log l WHERE l.accountId = :accountId ORDER BY l.id DESC LIMIT 1";
            List<Log> logs = em.createQuery(query1, Log.class)
                    .setParameter("accountId", accountId)
                    .getResultList();

            Log log = logs.isEmpty() ? null : logs.get(0);

            // cập nhật lại thời gian logout
            var query2 = "UPDATE Log l SET l.logoutTime = CURRENT_TIMESTAMP WHERE l.id = :logId";
            em.createQuery(query2)
                    .setParameter("logId", log.getId())
                    .executeUpdate();

            em.getTransaction().commit();
        }

    }
}
