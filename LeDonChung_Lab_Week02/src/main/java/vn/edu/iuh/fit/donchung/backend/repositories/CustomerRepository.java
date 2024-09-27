package vn.edu.iuh.fit.donchung.backend.repositories;

import vn.edu.iuh.fit.donchung.backend.models.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Interface CustomerRepository
 */
public interface CustomerRepository {
    /**
     * Tìm tất cả khách hàng
     * @return List<Customer>: Danh sách khách hàng
     */
    List<Customer> findAll();

    /**
     * Tìm khách hàng theo id
     * @param id là id của khách hàng
     * @return Customer nếu tìm thấy, ngược lại trả về null
     */
    Optional<Customer> findById(Long id);

    /**
     * Lưu khách hàng mới hoặc cập nhật thông tin khách hàng
     * @param customer là đối tượng Customer cần lưu
     * @return Customer nếu lưu thành công, ngược lại trả về null
     */
    Customer save(Customer customer);

    /**
     * Xóa khách hàng theo id
     * @param id là id của khách hàng cần xóa
     * @return boolean: true nếu xóa thành công, ngược lại trả về false
     */
    boolean delete(Long id);

    /**
     * Tìm khách hàng theo số điện thoại
     * @param phone là số điện thoại của khách hàng
     * @return  Customer nếu tìm thấy, ngược lại trả về null
     */
    Customer findByPhone(String phone);
}
