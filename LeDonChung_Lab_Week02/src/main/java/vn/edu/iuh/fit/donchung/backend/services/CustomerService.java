package vn.edu.iuh.fit.donchung.backend.services;

import vn.edu.iuh.fit.donchung.backend.dtos.CustomerDto;

import java.util.List;


/**
 * Interface CustomerService
 */
public interface CustomerService {

    /**
     * Lấy tất cả khách hàng
     * @return List<Customer>: Danh sách khách hàng
     */
    List<CustomerDto> getAll();

    /**
     * Lấy khách hàng theo id
     * @param id là id của khách hàng
     * @return Customer nếu tìm thấy, ngược lại trả về null
     */
    CustomerDto getById(Long id);

    /**
     * Lưu khách hàng mới hoặc cập nhật thông tin khách hàng
     * @param customer là đối tượng Customer cần lưu
     * @return Customer nếu lưu thành công, ngược lại trả về null
     */
    CustomerDto save(CustomerDto customer);

    /**
     * Xóa khách hàng theo id
     * @param id là id của khách hàng cần xóa
     * @return boolean: true nếu xóa thành công, ngược lại trả về false
     */
    boolean delete(Long id);


    /**
     * Lấy khách hàng theo số điện thoại
     * @param phone là số điện thoại của khách hàng
     * @return  Customer nếu tìm thấy, ngược lại trả về null
     */
    CustomerDto getByPhone(String phone);
}
