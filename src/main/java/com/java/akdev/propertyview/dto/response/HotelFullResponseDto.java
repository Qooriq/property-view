package com.java.akdev.propertyview.dto.response;

import com.java.akdev.propertyview.models.Address;
import com.java.akdev.propertyview.models.ArrivalTime;
import com.java.akdev.propertyview.models.Contacts;

import java.util.List;

public record HotelFullResponseDto(
        Long id,
        String name,
        String description,
        String brand,
        Address address,
        Contacts contacts,
        ArrivalTime arrivalTime,
        List<String> amenities
) {
}
