package com.example.liveasy.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    @NotNull(message = "Load ID cannot be null")
    private UUID loadId;

    @NotNull(message = "Transporter ID cannot be null")
    private String transporterId;

    @NotNull(message = "Proposed rate cannot be null")
    @Positive(message = "Proposed rate must be positive")
    private Double proposedRate;

    private String comment;

    public @NotNull(message = "Load ID cannot be null") UUID getLoadId() {
        return loadId;
    }

    public void setLoadId(@NotNull(message = "Load ID cannot be null") UUID loadId) {
        this.loadId = loadId;
    }

    public @NotNull(message = "Transporter ID cannot be null") String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(@NotNull(message = "Transporter ID cannot be null") String transporterId) {
        this.transporterId = transporterId;
    }

    public @NotNull(message = "Proposed rate cannot be null") @Positive(message = "Proposed rate must be positive") Double getProposedRate() {
        return proposedRate;
    }

    public void setProposedRate(@NotNull(message = "Proposed rate cannot be null") @Positive(message = "Proposed rate must be positive") Double proposedRate) {
        this.proposedRate = proposedRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}