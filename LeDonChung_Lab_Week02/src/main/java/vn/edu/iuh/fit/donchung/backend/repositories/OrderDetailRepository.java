package vn.edu.iuh.fit.donchung.backend.repositories;

import vn.edu.iuh.fit.donchung.backend.models.OrderDetail;

import java.util.List;

public interface OrderDetailRepository {
    /**
     * Lưu tất cả các chi tiết đơn hàng
     * @param orderDetails danh sách chi tiết đơn hàng cần lưu
     * @return danh sách chi tiết đơn hàng đã được lưu
     */
    List<OrderDetail> saveAll(List<OrderDetail> orderDetails);
}
