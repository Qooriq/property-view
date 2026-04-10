package com.java.akdev.propertyview.repository.specification;

import com.java.akdev.propertyview.entity.Hotel;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class HotelSpecification {

    public static Specification<Hotel> nameEquals(String name) {
        return (root, query, cb) -> name == null ? cb.conjunction() : cb.equal(root.get("name"), name);
    }

    public static Specification<Hotel> brandEquals(String brand) {
        return (root, query, cb) -> brand == null ? cb.conjunction() : cb.equal(root.get("brand"), brand);
    }

    public static Specification<Hotel> cityEquals(String city) {
        return (root, query, cb) -> city == null ? cb.conjunction() : cb.equal(root.get("address").get("city"), city);
    }

    public static Specification<Hotel> countryEquals(String country) {
        return (root, query, cb) -> country == null ? cb.conjunction() : cb.equal(root.get("address").get("country"), country);
    }

    public static Specification<Hotel> hasAmenity(String amenity) {
        return (root, query, cb) -> {
            if (amenity == null) return cb.conjunction();
            Join<Hotel, String> amenitiesJoin = root.join("amenities");
            return cb.equal(amenitiesJoin, amenity);
        };
    }
}