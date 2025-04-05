package com.example.liveasy.DTO;

import com.example.liveasy.Model.Facility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadDTO {

    @NotBlank(message = "Shipper ID cannot be blank")
    private String shipperId;

    @Valid
    @NotNull(message = "Facility details cannot be null")
    private FacilityDTO facility;

    @NotBlank(message = "Product type cannot be blank")
    private String productType;

    @NotBlank(message = "Truck type cannot be blank")
    private String truckType;

    @NotNull(message = "Number of trucks cannot be null")
    @Positive(message = "Number of trucks must be positive")
    private Integer noOfTrucks;

    @NotNull(message = "Weight cannot be null")
    @Positive(message = "Weight must be positive")
    private Double weight;

    private String comment;

    public @NotBlank(message = "Shipper ID cannot be blank") String getShipperId() {
        return shipperId;
    }

    public void setShipperId(@NotBlank(message = "Shipper ID cannot be blank") String shipperId) {
        this.shipperId = shipperId;
    }

    public @Valid @NotNull(message = "Facility details cannot be null") FacilityDTO getFacility() {
        return facility;
    }

    public void setFacility(@Valid @NotNull(message = "Facility details cannot be null") FacilityDTO facility) {
        this.facility = facility;
    }

    public @NotBlank(message = "Product type cannot be blank") String getProductType() {
        return productType;
    }

    public void setProductType(@NotBlank(message = "Product type cannot be blank") String productType) {
        this.productType = productType;
    }

    public @NotBlank(message = "Truck type cannot be blank") String getTruckType() {
        return truckType;
    }

    public void setTruckType(@NotBlank(message = "Truck type cannot be blank") String truckType) {
        this.truckType = truckType;
    }

    public @NotNull(message = "Number of trucks cannot be null") @Positive(message = "Number of trucks must be positive") Integer getNoOfTrucks() {
        return noOfTrucks;
    }

    public void setNoOfTrucks(@NotNull(message = "Number of trucks cannot be null") @Positive(message = "Number of trucks must be positive") Integer noOfTrucks) {
        this.noOfTrucks = noOfTrucks;
    }

    public @NotNull(message = "Weight cannot be null") @Positive(message = "Weight must be positive") Double getWeight() {
        return weight;
    }

    public void setWeight(@NotNull(message = "Weight cannot be null") @Positive(message = "Weight must be positive") Double weight) {
        this.weight = weight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
