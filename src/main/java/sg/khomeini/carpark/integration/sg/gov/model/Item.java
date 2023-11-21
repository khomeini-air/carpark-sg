package sg.khomeini.carpark.integration.sg.gov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime timestamp;

    @JsonProperty("carpark_data")
    private List<CarParkData> carParkData;
}
