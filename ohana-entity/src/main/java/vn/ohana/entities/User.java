package vn.ohana.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Column(name = "phone", unique = true, length = 11)
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    private Role role;

    @Column(name = "password", nullable = false, length = 25)
    private String password;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Column(name = "thumbnail_id")
    private String thumbnailId;

    @Column(name = "status", nullable = false)
    private UserStatus status;

    public User(Long poster) {
        this.id = poster;
    }
}