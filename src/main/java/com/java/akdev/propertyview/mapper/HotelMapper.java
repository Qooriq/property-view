package com.java.akdev.propertyview.mapper;

import com.java.akdev.propertyview.dto.request.HotelRequestDto;
import com.java.akdev.propertyview.dto.response.HotelFullResponseDto;
import com.java.akdev.propertyview.dto.response.HotelShortResponseDto;
import com.java.akdev.propertyview.entity.Hotel;

public class HotelMapper {

    private HotelMapper() {}

    public static HotelShortResponseDto mapToShortResponse(Hotel ent) {
        String address = ent.getAddress().getHouseNumber() +
                         " " +
                         ent.getAddress().getStreet() +
                         ", " +
                         ent.getAddress().getCity() +
                         ", " +
                         ent.getAddress().getPostCode() +
                         ", " +
                         ent.getAddress().getCountry();
        return new HotelShortResponseDto(
                ent.getId(),
                ent.getName(),
                ent.getDescription(),
                address,
                ent.getContacts().getPhone()
        );
    }

    public static HotelFullResponseDto mapToFullResponse(Hotel ent) {
        return new HotelFullResponseDto(
                ent.getId(),
                ent.getName(),
                ent.getDescription(),
                ent.getBrand(),
                ent.getAddress(),
                ent.getContacts(),
                ent.getTime(),
                ent.getAmenities()
        );
    }

    public static Hotel mapToEntity(HotelRequestDto dto) {
        return Hotel.builder()
                .name(dto.name())
                .description(dto.description())
                .brand(dto.brand())
                .address(dto.address())
                .contacts(dto.contacts())
                .time(dto.arrivalTime())
                .build();
    }
}
