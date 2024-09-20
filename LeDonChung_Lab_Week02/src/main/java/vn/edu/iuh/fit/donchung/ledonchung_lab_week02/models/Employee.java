package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.EmployeeStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employeess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "dob")
    private LocalDateTime dob;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "full_name", length = 150)
    private String fullName;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private EmployeeStatus status;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;

    // Getters và Setters
}
