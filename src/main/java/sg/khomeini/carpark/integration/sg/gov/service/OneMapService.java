package sg.khomeini.carpark.integration.sg.gov.service;

import sg.khomeini.carpark.integration.sg.gov.response.Wgs84Response;

public interface OneMapService {
    Wgs84Response getWgs84(final Double x, final Double y);
}
