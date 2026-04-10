package com.java.akdev.propertyview.service.impl;

import com.java.akdev.propertyview.dto.request.HotelRequestDto;
import com.java.akdev.propertyview.dto.response.HotelFullResponseDto;
import com.java.akdev.propertyview.dto.response.HotelShortResponseDto;
import com.java.akdev.propertyview.entity.Hotel;
import com.java.akdev.propertyview.mapper.HotelMapper;
import com.java.akdev.propertyview.repository.HotelRepository;
import com.java.akdev.propertyview.repository.specification.HotelSpecification;
import com.java.akdev.propertyview.service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<HotelShortResponseDto> findAll() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelMapper::mapToShortResponse)
                .toList();
    }

    @Override
    public HotelFullResponseDto findById(Long id) {
        return hotelRepository.findById(id)
                .map(HotelMapper::mapToFullResponse)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<HotelShortResponseDto> search(String name, String brand, String city, String country, String amenity) {
        Specification<Hotel> spec = Specification
                .where(HotelSpecification.nameEquals(name))
                .and(HotelSpecification.brandEquals(brand))
                .and(HotelSpecification.cityEquals(city))
                .and(HotelSpecification.countryEquals(country))
                .and(HotelSpecification.hasAmenity(amenity));
        return hotelRepository.findAll(spec).stream()
                .map(HotelMapper::mapToShortResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HotelShortResponseDto create(HotelRequestDto req) {
        return HotelMapper.mapToShortResponse(
                hotelRepository.saveAndFlush(HotelMapper.mapToEntity(req))
        );
    }

    @Override
    @Transactional
    public HotelFullResponseDto addAmenity(Long id, List<String> amenities) {
        return hotelRepository.findById(id)
                .map(ent -> {
                    var curAm = ent.getAmenities();

                    for (String amenity : amenities) {
                        if (!curAm.contains(amenity)) {
                            curAm.add(amenity);
                        }
                    }

                    hotelRepository.save(ent);
                    return HotelMapper.mapToFullResponse(ent);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Map<String, Long> getHistogram(String param) {
        return switch (param.toLowerCase()) {
            case "brand" -> toMap(hotelRepository.countByBrand());
            case "city" -> toMap(hotelRepository.countByCity());
            case "country" -> toMap(hotelRepository.countByCountry());
            case "amenities" -> toMap(hotelRepository.countByAmenities());
            default -> throw new IllegalArgumentException("Unsupported histogram parameter: " + param);
        };
    }

    private Map<String, Long> toMap(List<Object[]> rows) {
        return rows.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1],
                        (a, b) -> a
                ));
    }
}
