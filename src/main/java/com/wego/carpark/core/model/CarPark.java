package com.wego.carpark.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarPark {
    private String code;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer totalLots;
    private Integer availableLots;
}
