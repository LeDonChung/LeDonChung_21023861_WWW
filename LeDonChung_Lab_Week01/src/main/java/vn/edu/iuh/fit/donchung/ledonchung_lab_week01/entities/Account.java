package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(name = "Account.findByAccountIdAndPassword", query = "select a from Account a where a.accountId = :accountId and a.password = :password"),
        @NamedQuery(name = "Account.findAllAccountNotIsAdmin", query = "select a from Account a JOIN a.grantAccesses ga WHERE ga.role.id != 'admin'"),
        @NamedQuery(name = "Account.findByAccountId", query = "select a from Account a where a.accountId = :accountId")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<GrantAccess> grantAccesses = new LinkedHashSet<>();

}