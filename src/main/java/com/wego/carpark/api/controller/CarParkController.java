package com.wego.carpark.api.controller;

import com.wego.carpark.core.model.CarPark;
import com.wego.carpark.core.service.CarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/carparks")
@Validated
public class CarParkController {
    @Autowired
    private CarParkService carParkService;

    @GetMapping("/nearest")
    public ResponseEntity<List<CarPark>> findNearest(@RequestParam Double latitude,
                                                     @RequestParam Double longitude,
                                                     @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                                     @RequestParam(defaultValue = "10", name = "per_page") @Min(1) Integer perPage) {
        return ResponseEntity.ok(carParkService.findNearestAvailable(latitude, longitude, page, perPage));
    }

    @PostMapping("/convert-all/3414to4326")
    public ResponseEntity<Void> convertTo4326() {
        carParkService.convertTo4326();
        return ResponseEntity.noContent().build();
    }

}
