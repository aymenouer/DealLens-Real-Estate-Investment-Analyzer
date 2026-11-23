package com.deallens.deal;

import com.deallens.model.Deal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/deals")
public class DealController {
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    public ResponseEntity<List<Deal>> getAllDeals() {
        return ResponseEntity.ok(dealService.getAllDeals());
    }

    @GetMapping("{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable Integer id) {
        return dealService.getDealById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
