package ra.ojt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long id;
//    @Column(name = "user_id")
//    private Long userId;
//    @Column(name = "service_id")
//    private Long serviceId;
    @Column(columnDefinition = "TEXT")
    @NotBlank
    @Size(max = 1000)
    private String comment;
    @Column(length = 1)
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    @Column(name = "date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rateTime;

    @OneToOne
    @JoinColumn(name = "booking_id", unique = true)
    private Booking booking;

    @PrePersist
    protected void onCreate() {
        this.rateTime = LocalDateTime.now();
    }
}
