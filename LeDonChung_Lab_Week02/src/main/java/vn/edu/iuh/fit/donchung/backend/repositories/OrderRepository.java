package vn.edu.iuh.fit.donchung.backend.repositories;

import vn.edu.iuh.fit.donchung.backend.models.Order;

import java.util.List;

/**
 * Order repository
 */
public interface OrderRepository {
    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    List<Order> findByEmployeeId(Long employeeId);

}
