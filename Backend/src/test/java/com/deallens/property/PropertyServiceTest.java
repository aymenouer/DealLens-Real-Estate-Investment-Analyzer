package com.deallens.property;

import com.deallens.property.dto.YieldResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyServiceTest {
    private final PropertyService propertyService = new PropertyService();

    @Test
    void getAllPropertiesTest(){
        assertEquals(2,propertyService.getAllProperties().size());
    }
    @Test
    void shouldFindPropertyById() {
        assertTrue(propertyService.getPropertyById(1).isPresent());
    }
    @Test
    void shouldReturnPropertyWithCorrectId() {
        Property property = propertyService.getPropertyById(1).orElseThrow();
        assertEquals(1,property.id());
    }
    @Test
    void shouldReturnEmptyWhenPropertyDoesNotExist() {
        assertTrue(propertyService.getPropertyById(999).isEmpty());
    }


    @Test
    void shouldReturnPropertiesByCity() {
        assertEquals(2, propertyService
                .getPropertiesByCity("Hamburg")
                .size());
        assertTrue(propertyService
                .getPropertiesByCity("Hamburg")
                .stream()
                .allMatch(property -> property.city().equals("Hamburg")));
    }

    @Test
    void shouldReturnEmptyListWhenCityDoesNotExist() {
        assertTrue(propertyService.getPropertiesByCity("Berlin").isEmpty());
    }


    @Test
    void shouldReturnPropertiesWithinMaxPrice() {
        assertEquals(1, propertyService
                .getPropertiesWithinMaxPrice(200000)
                .size());
        assertEquals(1, propertyService
                .getPropertiesWithinMaxPrice(200000)
                .stream()
                .findFirst()
                .orElseThrow()
                .id());
    }

    @Test
    void shouldReturnEmptyListWhenNoPropertiesWithinMaxPrice() {
        assertTrue(propertyService
                .getPropertiesWithinMaxPrice(100000)
                .isEmpty());
    }

    @Test
    void shouldIncludePropertiesEqualToMaxPrice() {
        assertEquals(2, propertyService
                .getPropertiesWithinMaxPrice(230000)
                .size());
    }

    @Test
    void shouldReturnPropertyYieldWhenPropertyExists() {
        Property property = propertyService.getPropertyById(1).orElseThrow();

        YieldResponse expected = new YieldResponse(
                1,
                property.grossYield(),
                property.netYield()
        );
        assertTrue(propertyService
                .getPropertyYield(1)
                .isPresent());
        assertEquals(expected, propertyService
                .getPropertyYield(1)
                .orElseThrow());
    }

    @Test
    void shouldReturnEmptyYieldWhenPropertyDoesNotExist() {
        assertTrue(propertyService
                .getPropertyYield(999)
                .isEmpty());
    }


}
