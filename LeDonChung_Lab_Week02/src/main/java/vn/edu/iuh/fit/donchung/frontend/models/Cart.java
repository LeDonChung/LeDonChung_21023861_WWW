package vn.edu.iuh.fit.donchung.frontend.models;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.donchung.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.donchung.backend.dtos.OrderDetailDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Cart implements Serializable {
    private LocalDateTime orderDate;
    private CustomerDto customerDto;
    private EmployeeDto employeeDto;
    private List<CartDetail> cartDetails;
    public Integer getTotal() {
        return cartDetails.size();
    }
    public Double getTotalPrice() {
        return cartDetails.stream().mapToDouble(
                cartDetail -> cartDetail.getPrice() * cartDetail.getQuantity()
        ).sum();
    }
}
