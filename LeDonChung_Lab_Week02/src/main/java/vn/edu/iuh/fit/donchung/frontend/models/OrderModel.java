package vn.edu.iuh.fit.donchung.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.donchung.backend.dtos.OrderDto;

import java.util.List;

@Remote
public interface OrderModel {
    /**
     * Save order
     * @return OrderDto saved
     */
    OrderDto save(OrderDto orderDto);

    /**
     * Lấy tất cả order
     * @return List<OrderDto> tất cả order
     */
    List<OrderDto> getAll();

    /**
     * Lấy tất cả order của employee
     * @return List<OrderDto> tất cả order
     */
    List<OrderDto> getByEmployeeId(Long employeeId);

    /**
     * Lấy order theo id
     * @param id id của order
     * @return OrderDto order
     */
    OrderDto getById(Long id);
}
