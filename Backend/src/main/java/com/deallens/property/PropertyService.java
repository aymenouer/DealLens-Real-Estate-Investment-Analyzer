package com.deallens.property;


import com.deallens.model.Property;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    List<Property> properties;

    public PropertyService() {
        properties =  List.of(
                // id, address, city, squareMeters, purchasePrice, allocableCosts, nonAllocableCosts
                new Property(1, "Musterstraße 1", "Hamburg", 75, 200000, 200, 50),
                new Property(2, "Beispielweg 12", "Hamburg", 65, 230000, 100, 60)
        );
    }


    public List<Property> getAllProperties() {
        return properties;
    }

    public Optional<Property> getPropertyById(Integer id) {
        return properties.stream()
                .filter(property -> property.id().equals(id))
                .findFirst();
    }

    public List<Property> getPropertiesByCity(String city) {
        return properties.stream()
                .filter(property -> property.city().equals(city))
                .toList();
    }

    public List<Property> getPropertiesWithinMaxPrice(double maxPrice) {
        return properties.stream()
                .filter(property -> property.purchasePrice() <= maxPrice)
                .toList();
    }


}
