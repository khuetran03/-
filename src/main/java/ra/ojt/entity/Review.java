package ra.ojt.entity;

import jakarta.persistence.*;
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
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "service_id")
    private Long serviceId;
    @Column(columnDefinition = "TEXT")
    private String content;
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
