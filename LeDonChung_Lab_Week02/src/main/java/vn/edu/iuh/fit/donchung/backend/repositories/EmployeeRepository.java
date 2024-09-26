package vn.edu.iuh.fit.donchung.backend.repositories;

import vn.edu.iuh.fit.donchung.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.donchung.backend.models.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Interface EmployeeRepository
 */
public interface EmployeeRepository {
    /**
     * Tìm tất cả nhân viên
     * @return List<Employee>: Danh sách nhân viên
     */
    List<Employee> findAll();

    /**
     * Tìm nhân viên theo id
     * @param id là id của nhân viên
     * @return Employee nếu tìm thấy, ngược lại trả về null
     */
    Optional<Employee> findById(Long id);

    /**
     * Lưu nhân viên mới hoặc cập nhật thông tin nhân viên
     * @param employee là đối tượng Employee cần lưu
     * @return Employee nếu lưu thành công, ngược lại trả về null
     */
    Employee save(Employee employee);

    /**
     * Cập nhật trạng thái của nhân viên
     * @param id là id của nhân viên cần cập nhật
     * @Param status là trạng thái cần cập nhật
     * - TERMINATED(-1) là nhân viên đã nghỉ việc
     * - ACTIVE(1) là nhân viên đang làm việc
     * - IN_ACTIVE(0) là nhân viên không hoạt động
     * @return boolean: true nếu xóa thành công, ngược lại trả về false
     */
    Employee updateStatus(Long id, EmployeeStatus status);
}
