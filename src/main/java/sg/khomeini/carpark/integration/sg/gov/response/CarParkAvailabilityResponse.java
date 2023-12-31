package sg.khomeini.carpark.integration.sg.gov.response;

import sg.khomeini.carpark.integration.sg.gov.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarParkAvailabilityResponse {
    private List<Item> items;
}
