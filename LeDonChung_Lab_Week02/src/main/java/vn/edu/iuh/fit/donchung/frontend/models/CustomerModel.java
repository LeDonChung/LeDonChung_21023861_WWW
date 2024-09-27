package vn.edu.iuh.fit.donchung.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.donchung.backend.dtos.CustomerDto;

@Remote
public interface CustomerModel {
    CustomerDto findByPhone(String phone);
}
