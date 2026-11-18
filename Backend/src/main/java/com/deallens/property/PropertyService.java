package com.deallens.property;


import com.deallens.property.dto.YieldResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    List<Property> properties;

    public PropertyService() {
        properties =  List.of(
                new Property(1,"Hamburg",200000,750,200,500),
                new Property(2,"Hamburg",230000,650,100,600)
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
                .filter(property -> property.price() <= maxPrice)
                .toList();
    }

    public Optional<YieldResponse>  getPropertyYield(Integer id) {
        return getPropertyById(id)
                .map(property ->
                        new YieldResponse(id,property.grossYield(),property.netYield())
                );
    }





}
