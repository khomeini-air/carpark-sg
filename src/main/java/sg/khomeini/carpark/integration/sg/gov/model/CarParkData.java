package sg.khomeini.carpark.integration.sg.gov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarParkData {
    @JsonProperty("carpark_number")
    private String number;

    @JsonProperty("carpark_info")
    private List<LotInfo> carParkInfo;

    @JsonProperty("update_datetime")
    private LocalDateTime updatedAt;
}
