package com.java.akdev.propertyview.controller;

import com.java.akdev.propertyview.dto.request.HotelRequestDto;
import com.java.akdev.propertyview.dto.response.HotelFullResponseDto;
import com.java.akdev.propertyview.dto.response.HotelShortResponseDto;
import com.java.akdev.propertyview.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelShortResponseDto>> findAllHotels() {
        return ResponseEntity.ok().body(hotelService.findAll());
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelFullResponseDto> findHotelById(@PathVariable Long id) {
        return ResponseEntity.ok().body(hotelService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelShortResponseDto>> findHotelByParam(@RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) String brand,
                                                                  @RequestParam(required = false) String city,
                                                                  @RequestParam(required = false) String country,
                                                                  @RequestParam(required = false) String amenity) {
        return ResponseEntity.ok().body(hotelService.search(name, brand, city, country, amenity));
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelShortResponseDto> createHotel(@RequestBody @Valid HotelRequestDto req) {
        return ResponseEntity.ok().body(hotelService.create(req));
    }

    @PostMapping("/hotel/{id}/amenities")
    public ResponseEntity<HotelFullResponseDto> addAmenity(
            @RequestBody List<String> amenities,
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addAmenity(id, amenities));
    }

    @GetMapping("/histogramm/{param}")
    public ResponseEntity<Map<String, Long>> getHistogramm(@PathVariable String param) {
        return ResponseEntity.ok().body(hotelService.getHistogram(param));
    }
}
