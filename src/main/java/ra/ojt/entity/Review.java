package ra.ojt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    @NotNull
    private Long id;
//    @Column(name = "user_id")
//    private Long userId;
//    @Column(name = "service_id")
//    private Long serviceId;
    @Column(columnDefinition = "TEXT")
    @NotBlank
    @Size(max = 1000)
    private String content;
    @Column(length = 1)
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @Column(name = "date_time")
    private LocalDateTime rateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private Service service;

    @PrePersist
    protected void onCreate() {
        this.rateTime = LocalDateTime.now();
    }
}
