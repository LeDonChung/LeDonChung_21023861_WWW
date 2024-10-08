package vn.edu.iuh.fit.donchung.backend.services;

import vn.edu.iuh.fit.donchung.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.donchung.backend.dtos.EmployeeDto;

import java.util.List;

/**
 * Interface EmployeeService
 */
public interface EmployeeService {
    /**
     * Lấy danh sách nhân viên
     * @return List<Employee>: Danh sách nhân viên
     */
    List<EmployeeDto> getAll();

    /**
     * Tìm nhân viên theo id
     * @param id là id của nhân viên
     * @return Employee nếu tìm thấy, ngược lại trả về null
     */
    EmployeeDto getById(Long id);

    /**
     * Lưu nhân viên mới hoặc cập nhật thông tin nhân viên
     * @param employee là đối tượng Employee cần lưu
     * @return Employee nếu lưu thành công, ngược lại trả về null
     */
    EmployeeDto save(EmployeeDto employee);

    /**
     * Cập nhật trạng thái của nhân viên
     * @param id là id của nhân viên cần cập nhật
     * @param status là trạng thái cần cập nhật
     * @return boolean: true nếu xóa thành công, ngược lại trả về false
     */
    EmployeeDto updateStatus(Long id, EmployeeStatus status);

    /**
     * Tìm nhân viên theo số điện thoại
     * @param phone là số điện thoại của nhân viên
     * @return Employee nếu tìm thấy, ngược lại trả về null
     */
    EmployeeDto getByPhone(String phone);
}
