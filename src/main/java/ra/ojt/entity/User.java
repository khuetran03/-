package ra.ojt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Size(max = 64)
    @Column(name = "full_name", nullable = false, length = 64)
    private String fullName;

    @NotBlank
    @Email(message = "Email không đúng định dạng!")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max = 15)
    @Column(name = "phone", length = 15, unique = true)
    private String phone;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

}

