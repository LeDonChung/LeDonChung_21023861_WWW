package vn.edu.iuh.fit.donchung.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.donchung.backend.converters.EmployeeStatusConverter;
import vn.edu.iuh.fit.donchung.backend.enums.EmployeeStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries(
        {
                @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
                @NamedQuery(name = "Employee.findByPhoneAndStatus", query = "SELECT e FROM Employee e WHERE e.phone = :phone AND e.status = :status")
        }
)
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
