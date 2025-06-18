package ra.ojt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Duration duration;

    @OneToMany(mappedBy = "service",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> serviceReviews;
}
