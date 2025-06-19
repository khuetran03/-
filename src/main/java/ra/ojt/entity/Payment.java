package ra.ojt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod; // PAYPAL

    @Column(name = "transaction_id", unique = true)
    private String transactionId; // PayPal Order ID

    @Column(name = "payment_status")
    private String paymentStatus; // CREATED, APPROVED, COMPLETED, FAILED

    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}
