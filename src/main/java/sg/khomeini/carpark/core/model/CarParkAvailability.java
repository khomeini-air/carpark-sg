package sg.khomeini.carpark.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarParkAvailability {
    private CarPark carPark;
    private Integer totalLots;
    private Integer availableLots;
}
