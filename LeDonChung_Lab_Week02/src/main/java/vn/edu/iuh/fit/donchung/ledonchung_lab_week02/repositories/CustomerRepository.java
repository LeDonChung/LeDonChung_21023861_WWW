package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.repositories;

import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Customer;

import java.util.List;

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
    Customer findById(Long id);

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

}
