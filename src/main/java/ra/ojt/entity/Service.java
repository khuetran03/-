package ra.ojt.entity;

import jakarta.persistence.*;
import lombok.*;
import ra.ojt.config.enums.Duration;

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

    @Column(name = "service_name")
    private String name;

    @Column(name = "service_description")
    private String description;

    @Column(name = "service_price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_duration")
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;
}


