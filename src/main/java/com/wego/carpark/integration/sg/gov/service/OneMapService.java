package com.wego.carpark.integration.sg.gov.service;

import com.wego.carpark.integration.sg.gov.response.Wgs84Response;

public interface OneMapService {
    Wgs84Response getWgs84(final Double x, final Double y);
}
