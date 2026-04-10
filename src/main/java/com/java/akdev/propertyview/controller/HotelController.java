package com.java.akdev.propertyview.controller;

import com.java.akdev.propertyview.dto.request.HotelRequestDto;
import com.java.akdev.propertyview.dto.response.HotelFullResponseDto;
import com.java.akdev.propertyview.dto.response.HotelShortResponseDto;
import com.java.akdev.propertyview.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Hotel API", description = "Управление отелями")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    @Operation(summary = "Получить список всех отелей с краткой информацией")
    public ResponseEntity<List<HotelShortResponseDto>> findAllHotels() {
        return ResponseEntity.ok().body(hotelService.findAll());
    }

    @GetMapping("/hotels/{id}")
    @Operation(summary = "Получить расширенную информацию об отеле")
    public ResponseEntity<HotelFullResponseDto> findHotelById(@PathVariable Long id) {
        return ResponseEntity.ok().body(hotelService.findById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск отелей по параметрам")
    public ResponseEntity<List<HotelShortResponseDto>> findHotelByParam(@RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) String brand,
                                                                  @RequestParam(required = false) String city,
                                                                  @RequestParam(required = false) String country,
                                                                  @RequestParam(required = false) String amenity) {
        return ResponseEntity.ok().body(hotelService.search(name, brand, city, country, amenity));
    }

    @PostMapping("/hotels")
    @Operation(summary = "Создать новый отель")
    public ResponseEntity<HotelShortResponseDto> createHotel(@RequestBody @Valid HotelRequestDto req) {
        return ResponseEntity.ok().body(hotelService.create(req));
    }

    @PostMapping("/hotel/{id}/amenities")
    @Operation(summary = "Добавить удобства отелю")
    public ResponseEntity<HotelFullResponseDto> addAmenity(
            @RequestBody List<String> amenities,
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addAmenity(id, amenities));
    }

    @GetMapping("/histogramm/{param}")
    @Operation(summary = "Получить гистограмму по параметру (brand, city, country, amenities)")
    public ResponseEntity<Map<String, Long>> getHistogramm(@PathVariable String param) {
        return ResponseEntity.ok().body(hotelService.getHistogram(param));
    }
}
