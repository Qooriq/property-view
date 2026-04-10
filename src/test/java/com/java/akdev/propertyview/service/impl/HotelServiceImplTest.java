package com.java.akdev.propertyview.service.impl;

import com.java.akdev.propertyview.dto.request.HotelRequestDto;
import com.java.akdev.propertyview.entity.Hotel;
import com.java.akdev.propertyview.models.Address;
import com.java.akdev.propertyview.models.ArrivalTime;
import com.java.akdev.propertyview.models.Contacts;
import com.java.akdev.propertyview.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @InjectMocks
    HotelServiceImpl hotelServiceImpl;

    @Mock
    HotelRepository hotelRepository;

    Hotel testEntity;
    Address testAddress;
    ArrivalTime testArrivalTime;
    Contacts testContacts;
    HotelRequestDto req;

    @BeforeEach
    void setUp() {
        testAddress = new Address(
                9L, "Pobediteley Avenue", "Minsk", "Belarus", "220004"
        );
        testContacts = new Contacts(
                "+375 17 309-80-00",
                "doubletreeminsk.info@hilton.com"
        );
        testArrivalTime = new ArrivalTime(
                "14:00", "12:00"
        );
        testEntity = Hotel.builder()
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor")
                .brand("Hilton")
                .address(testAddress)
                .time(testArrivalTime)
                .contacts(testContacts)
                .amenities(new ArrayList<>())
                .build();

        req = new HotelRequestDto(
                "DoubleTree by Hilton Minsk",
                "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor",
                "Hilton",
                testAddress,
                testContacts,
                testArrivalTime
        );
    }

    @Test
    void findAll() {
        when(hotelRepository.findAll())
                .thenReturn(List.of(testEntity));

        var result = hotelServiceImpl.findAll();

        assertNotNull(result);
        assertEquals(result.size(), 1);
    }

    @Test
    void findById() {
        when(hotelRepository.findById(1L))
                .thenReturn(Optional.of(testEntity));

        var result = hotelServiceImpl.findById(1L);

        assertNotNull(result);
        assertEquals(result.address(), testAddress);
        assertEquals(result.arrivalTime(), testArrivalTime);
    }


    @Test
    void create() {
        when(hotelRepository.saveAndFlush(any()))
                .thenReturn(testEntity);

        var result = hotelServiceImpl.create(req);

        assertNotNull(result);
        assertEquals(result.address(), testAddress.getHouseNumber() + " " + testAddress.getStreet() + ", " + testAddress.getCity() + ", " + testAddress.getPostCode() + ", " + testAddress.getCountry());
    }

    @Test
    void addAmenity() {
        when(hotelRepository.findById(1L))
                .thenReturn(Optional.of(testEntity));

        var res = hotelServiceImpl.addAmenity(1L, List.of("free"));

        assertNotNull(res);
    }

    @Test
    void getHistogram() {
        when(hotelRepository.countByCity())
                .thenReturn(List.<Object[]>of(new Object[]{"Minsk", 1L}));

        var res = hotelServiceImpl.getHistogram("city");

        assertNotNull(res);
        assertEquals(res.size(), 1L);
    }
}