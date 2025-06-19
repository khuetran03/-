package ra.ojt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    @NotBlank
    @Size(max = 64)
    @Column(name = "service_name", nullable = false, length = 64)
    private String name;

    @NotBlank
    @Size(max = 254)
    @Column(name = "service_description", nullable = false, length = 254)
    private String description;

    @DecimalMin("0.0")
    @Digits(integer = 8, fraction = 2)
    @Column(name = "service_price", nullable = false)
    private BigDecimal price;

    @NotBlank
    @Column(name = "service_duration", nullable = false)
    private String duration;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private User staff;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;
}


