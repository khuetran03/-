package ra.ojt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ra.ojt.config.enums.PaymentStatus;

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
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking booking;

    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "payment_method")
    private String paymentMethod; // PAYPAL

    @Column(name = "transaction_id")
    private String transactionId; // PayPal Order ID

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status",nullable = false)
    private PaymentStatus paymentStatus; // CREATED, APPROVED, COMPLETED, FAILED

    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}
