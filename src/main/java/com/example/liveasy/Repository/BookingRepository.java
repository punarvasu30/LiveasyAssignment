package com.example.liveasy.Repository;

import com.example.liveasy.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByLoadId(UUID loadId);

    List<Booking> findByTransporterId(String transporterId);

    // Need to join with Load to get shipperId
    @Query("SELECT b FROM Booking b JOIN Load l ON b.loadId = l.id WHERE l.shipperId = :shipperId")
    List<Booking> findByShipperId(@Param("shipperId") String shipperId);

    // Need to join with Load to get shipperId
    @Query("SELECT b FROM Booking b JOIN Load l ON b.loadId = l.id WHERE l.shipperId = :shipperId AND b.transporterId = :transporterId")
    List<Booking> findByShipperIdAndTransporterId(@Param("shipperId") String shipperId, @Param("transporterId") String transporterId);
}
