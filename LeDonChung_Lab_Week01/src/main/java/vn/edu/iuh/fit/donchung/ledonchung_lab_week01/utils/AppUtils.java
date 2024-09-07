package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class AppUtils {
    public static final String PERSISTENCE_UNIT_NAME = "LeDonChung MariaDB";

    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    }
}
