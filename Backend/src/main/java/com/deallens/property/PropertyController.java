package com.deallens.property;

import com.deallens.property.dto.YieldResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/properties")
public class PropertyController {
    private final PropertyService  propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties(@RequestParam(required = false) String city) {
        return ResponseEntity.ok(
                Optional.ofNullable(city)
                        .filter(cityValue -> !cityValue.isBlank())
                        .map(propertyService::getPropertiesByCity)
                        .orElseGet(propertyService::getAllProperties)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Integer id) {
        return propertyService.getPropertyById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/shortlist")
    public ResponseEntity<List<Property>> getPropertiesWithinMaxPrice(@RequestParam(required = false) double maxPrice) {
        return ResponseEntity.ok(
                Optional.ofNullable(maxPrice)
                        .filter(maxPriceValue -> !maxPriceValue.isNaN())
                        .map(propertyService::getPropertiesWithinMaxPrice)
                        .orElseGet(propertyService::getAllProperties)
        );
    }

    @GetMapping("/{id}/yield")
    public ResponseEntity<YieldResponse> getPropertiesYield(@PathVariable Integer id) {
        return propertyService.getPropertyYield(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
