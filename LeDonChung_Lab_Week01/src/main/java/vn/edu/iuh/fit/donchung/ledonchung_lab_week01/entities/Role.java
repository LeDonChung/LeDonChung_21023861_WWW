package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@NamedQueries({
        @NamedQuery(name = "Role.findAll", query = "select r from Role r")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @Column(name = "role_id", nullable = false, length = 50)
    private String roleId;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<GrantAccess> grantAccesses = new LinkedHashSet<>();
}