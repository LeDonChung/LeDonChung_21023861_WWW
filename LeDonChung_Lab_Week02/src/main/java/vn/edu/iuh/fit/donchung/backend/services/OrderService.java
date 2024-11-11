package vn.edu.iuh.fit.donchung.backend.services;

import vn.edu.iuh.fit.donchung.backend.dtos.OrderDto;

import java.util.List;

public interface OrderService {
    /**
     * Lấy tất cả các đơn hàng
     * @return danh sách đơn hàng
     */
    List<OrderDto> findAll();

    /**
     * Lấy đơn hàng theo id
     * @param id id của đơn hàng cần lấy
     * @return đơn hàng
     */
    OrderDto findById(Long id);

    /**
     * Lưu đơn hàng
     * @param order đơn hàng cần lưu
     * @return đơn hàng đã được lưu
     */
    OrderDto save(OrderDto order);

    List<OrderDto> findByEmployeeId(Long employeeId);
}
