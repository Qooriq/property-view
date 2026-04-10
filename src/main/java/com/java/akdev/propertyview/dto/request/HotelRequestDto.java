package com.java.akdev.propertyview.dto.request;

import com.java.akdev.propertyview.models.Address;
import com.java.akdev.propertyview.models.ArrivalTime;
import com.java.akdev.propertyview.models.Contacts;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record HotelRequestDto(
        @NotBlank String name,
        String description,
        @NotBlank String brand,
        @Valid Address address,
        @Valid Contacts contacts,
        @Valid ArrivalTime arrivalTime
) {
}
