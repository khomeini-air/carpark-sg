package com.wego.carpark.integration.sg.gov.service.impl;

import com.wego.carpark.integration.sg.gov.response.CarParkAvailabilityResponse;
import com.wego.carpark.integration.sg.gov.service.DataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DataServiceImpl implements DataService {
    @Value("${integration.api.sg.gov.baseurl}")
    private String apiBaseUrl;

    @Value("${integration.api.sg.gov.path.carpark.availability}")
    private String apiPath;

    @Override
    public CarParkAvailabilityResponse getCarParkAvailability() {
        return getWebClient()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CarParkAvailabilityResponse.class)
                .block();
    }

    private WebClient getWebClient() {
        return WebClient
                .builder()
                .baseUrl(String.format("%s%s",apiBaseUrl,apiPath))
                .exchangeStrategies(ExchangeStrategies
                        .builder()
                        .codecs(codecs -> codecs
                                .defaultCodecs()
                                .maxInMemorySize(500 * 1024))
                        .build())
                .build();
    }
}
