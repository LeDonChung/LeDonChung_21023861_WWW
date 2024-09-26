package vn.edu.iuh.fit.donchung.backend.dtos;

import lombok.*;
import vn.edu.iuh.fit.donchung.backend.models.Order;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderDto implements Serializable {
    private Long id;
    private LocalDateTime orderDate;
    private CustomerDto customer;
    private EmployeeDto employee;
    private List<OrderDetailDto> orderDetails;
}