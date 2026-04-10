package com.java.akdev.propertyview.service;

import com.java.akdev.propertyview.dto.request.HotelRequestDto;
import com.java.akdev.propertyview.dto.response.HotelFullResponseDto;
import com.java.akdev.propertyview.dto.response.HotelShortResponseDto;

import java.util.List;
import java.util.Map;

public interface HotelService {

    List<HotelShortResponseDto> findAll();

    HotelFullResponseDto findById(Long id);

    List<HotelShortResponseDto> search(String name, String brand, String city, String country, String amenity);

    HotelShortResponseDto create(HotelRequestDto req);

    HotelFullResponseDto addAmenity(Long id, List<String> amenities);

    Map<String, Long> getHistogram(String param);
}
