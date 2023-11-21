package sg.khomeini.carpark.core.service;

import sg.khomeini.carpark.core.model.CarPark;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface CarParkService {
    List<CarPark> findNearestAvailable(@NotNull final Double latitude,
                                       @NotNull final Double longitude,
                                       @NotNull @Min(0) final Integer page,
                                       @NotNull @Min(1) Integer size);
    void updateAvailability();
    void convertTo4326();
}
