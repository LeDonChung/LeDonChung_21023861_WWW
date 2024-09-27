package vn.edu.iuh.fit.donchung.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.donchung.backend.dtos.EmployeeDto;

@Remote
public interface AccountModel {
    public Account findByPhone(String phone);
}
