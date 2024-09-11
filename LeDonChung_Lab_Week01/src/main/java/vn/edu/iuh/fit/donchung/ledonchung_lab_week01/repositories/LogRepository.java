package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories;

public interface LogRepository {
    void saveTimeLogin(String accountId);
    void saveTimeLogout(String accountId);
}
