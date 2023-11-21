package sg.khomeini.carpark.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ConverterUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    public static <T> T convertValue(final Object fromValue, final Class<T>  toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }
}
