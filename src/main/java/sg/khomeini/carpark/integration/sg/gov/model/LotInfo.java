package sg.khomeini.carpark.integration.sg.gov.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LotInfo {
    @JsonProperty("total_lots")
    private Integer totalLots;

    @JsonProperty("lots_available")
    private Integer availableLots;

    @JsonProperty("lot_type")
    private String lotType;
}
