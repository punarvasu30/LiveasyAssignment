package com.example.liveasy.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDTO {

    @NotBlank(message = "Loading point cannot be blank")
    private String loadingPoint;

    @NotBlank(message = "Unloading point cannot be blank")
    private String unloadingPoint;

    @NotNull(message = "Loading date cannot be null")
    private Timestamp loadingDate;

    @NotNull(message = "Unloading date cannot be null")
    private Timestamp unloadingDate;

    public @NotBlank(message = "Loading point cannot be blank") String getLoadingPoint() {
        return loadingPoint;
    }

    public void setLoadingPoint(@NotBlank(message = "Loading point cannot be blank") String loadingPoint) {
        this.loadingPoint = loadingPoint;
    }

    public @NotBlank(message = "Unloading point cannot be blank") String getUnloadingPoint() {
        return unloadingPoint;
    }

    public void setUnloadingPoint(@NotBlank(message = "Unloading point cannot be blank") String unloadingPoint) {
        this.unloadingPoint = unloadingPoint;
    }

    public @NotNull(message = "Unloading date cannot be null") Timestamp getUnloadingDate() {
        return unloadingDate;
    }

    public void setUnloadingDate(@NotNull(message = "Unloading date cannot be null") Timestamp unloadingDate) {
        this.unloadingDate = unloadingDate;
    }

    public @NotNull(message = "Loading date cannot be null") Timestamp getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(@NotNull(message = "Loading date cannot be null") Timestamp loadingDate) {
        this.loadingDate = loadingDate;
    }
}