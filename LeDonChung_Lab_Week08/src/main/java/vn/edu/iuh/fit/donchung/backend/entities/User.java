package vn.edu.iuh.fit.donchung.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "middleName", length = 50)
    private String middleName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password_hash", nullable = true, length = 32)
    private String passwordHash;

    @Column(name = "registered_at", nullable = false)
    private Timestamp registeredAt;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "intro")
    private String intro;

    @Column(name = "profile")
    private String profile;

    @OneToMany(mappedBy = "author")
    private Set<Post> posts;

}