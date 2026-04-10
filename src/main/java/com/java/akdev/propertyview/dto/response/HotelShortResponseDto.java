package com.java.akdev.propertyview.dto.response;

public record HotelShortResponseDto(
        Long id,
        String name,
        String description,
        String address,
        String phone
) {
}
