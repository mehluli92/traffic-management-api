package com.traffic.tmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficViolations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private Double latitude;
    private Double longitude;
    private String number_plate;
    private String image_url;
    private Double fine_amount;
    private String status; //paid or unpaid

    //Many to one relationship with violation type
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "violation_type_id", nullable = true)
    private ViolationType violationType;

    @OneToOne(mappedBy = "trafficViolation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payments payment;
    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
