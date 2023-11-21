package sg.khomeini.carpark.integration.sg.gov.service.impl;

import sg.khomeini.carpark.integration.sg.gov.response.Wgs84Response;
import sg.khomeini.carpark.integration.sg.gov.service.OneMapService;
import io.netty.handler.logging.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class OneMapServiceImpl implements OneMapService {
    private static final String X_PARAM = "X";
    private static final String Y_PARAM = "Y";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    @Value("${integration.api.sg.gov.onemap.baseurl}")
    private String oneMapBaseUrl;

    @Value("${integration.api.sg.gov.onemap.path.convert.3414to4326}")
    private String apiPath;

    @Value("${integration.api.sg.gov.onemap.token}")
    private String apiToken;

    @Override
    public Wgs84Response getWgs84(final Double x, final Double y) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(X_PARAM, String.valueOf(x));
        params.add(Y_PARAM, String.valueOf(y));

        final Map<String, List<String>> headers = Map.of(AUTHORIZATION_HEADER, List.of(apiToken));

        final HttpClient httpClient = HttpClient.create()
                .wiretap(this.getClass().getCanonicalName(), LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
        final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        final WebClient webClient = WebClient
                .builder()
                .baseUrl(oneMapBaseUrl)
                .clientConnector(connector)
                .build();

        return webClient
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path(apiPath).queryParams(params).build())
                .bodyValue(Collections.emptyMap())
                .headers(h -> h.putAll(headers))
                .accept(new MediaType[]{MediaType.APPLICATION_JSON})
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(Wgs84Response.class)
                .block();
    }
}
