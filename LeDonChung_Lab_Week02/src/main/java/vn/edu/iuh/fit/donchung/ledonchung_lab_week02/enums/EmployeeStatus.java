package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums;

import lombok.Getter;


/**
 * EmployeeStatus
 * - TERMINATED(-1) là nhân viên đã nghỉ việc
 * - ACTIVE(1) là nhân viên đang làm việc
 * - IN_ACTIVE(0) là nhân viên không hoạt động
 */
@Getter
public enum EmployeeStatus {
    TERMINATED(-1),
    ACTIVE(1),
    IN_ACTIVE(0);

    private int value;

    private EmployeeStatus(int value) {
        this.value = value;
    }

    public static EmployeeStatus fromValue(int status) {
        for (EmployeeStatus employeeStatus : EmployeeStatus.values()) {
            if (employeeStatus.value == status) {
                return employeeStatus;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

