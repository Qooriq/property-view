package com.java.akdev.propertyview.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ArrivalTime {

    @NotBlank
    private String checkIn;

    private String checkOut;
}
