package sg.khomeini.carpark.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sg.khomeini.carpark.core.model.CarPark;
import sg.khomeini.carpark.core.service.CarParkService;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/carparks")
@Validated
public class CarParkController {
    @Autowired
    private CarParkService carParkService;

    @GetMapping("/nearest")
    @ApiOperation(value = "Nearest Car Park", notes = "Get nearest available car parks to your current location")
    public ResponseEntity<List<CarPark>> findNearest(
            @ApiParam(value = "Your current latitude")
            @RequestParam final Double latitude,
            @ApiParam(value = "Your current longitude")
            @RequestParam final Double longitude,
            @RequestParam(defaultValue = "0") @Min(0) final Integer page,
            @RequestParam(defaultValue = "10", name = "size") @Min(1) final Integer size) {
        return ResponseEntity.ok(carParkService.findNearestAvailable(latitude, longitude, page, size));
    }

    @PostMapping("/convert-all/3414to4326")
    @ApiOperation(value = "A tool API to convert existing car park locations from x-y (EPSG:3414) to lat-long (EPSG:4326)")
    public ResponseEntity<Void> convertTo4326() {
        carParkService.convertTo4326();
        return ResponseEntity.noContent().build();
    }

}
