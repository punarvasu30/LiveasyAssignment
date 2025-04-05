package com.example.liveasy.Model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.sql.Timestamp;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facility {
    private String loadingPoint;
    private String unloadingPoint;
    private Timestamp loadingDate;
    private Timestamp unloadingDate;
}
