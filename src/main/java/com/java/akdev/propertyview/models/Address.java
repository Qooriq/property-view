package com.java.akdev.propertyview.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Address {

    @NotNull
    private Long houseNumber;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @NotBlank
    private String postCode;
}
