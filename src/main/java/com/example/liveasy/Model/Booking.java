package com.example.liveasy.Model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // Changed from bookingId to id for consistency

    @Column(nullable = false)
    private UUID loadId;

    @Column(nullable = false)
    private String transporterId;

    @Column(nullable = false)
    private double proposedRate;

    private String comment;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    @Column(nullable = false)
    private Timestamp requestedAt;
}
