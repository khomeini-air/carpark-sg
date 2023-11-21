package sg.khomeini.carpark.core.service.impl;

import sg.khomeini.carpark.core.dao.entity.CarParkAvailabilityEntity;
import sg.khomeini.carpark.core.dao.entity.CarParkEntity;
import sg.khomeini.carpark.core.dao.repository.CarParkRepository;
import sg.khomeini.carpark.core.model.CarPark;
import sg.khomeini.carpark.core.service.CarParkService;
import sg.khomeini.carpark.integration.sg.gov.model.CarParkData;
import sg.khomeini.carpark.integration.sg.gov.model.Item;
import sg.khomeini.carpark.integration.sg.gov.response.CarParkAvailabilityResponse;
import sg.khomeini.carpark.integration.sg.gov.response.Wgs84Response;
import sg.khomeini.carpark.integration.sg.gov.service.DataService;
import sg.khomeini.carpark.integration.sg.gov.service.OneMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Validated
public class CarParkServiceImpl implements CarParkService {
    @Autowired
    private CarParkRepository carParkRepository;

    @Autowired
    private DataService dataService;

    @Autowired
    private OneMapService oneMapService;

    @Override
    public List<CarPark> findNearestAvailable(@NotNull final Double latitude,
                                              @NotNull final Double longitude,
                                              @NotNull @Min(0) final Integer page,
                                              @NotNull @Min(1) final Integer size) {
        final List<CarParkEntity> carParkEntities = carParkRepository.findAllAvailable(latitude, longitude, PageRequest.of(page, size));
        return carParkEntities.stream().map(e -> convertToCarPark(e)).collect(Collectors.toList());
    }

    @Override
    public void updateAvailability() {
        final CarParkAvailabilityResponse govResponse = dataService.getCarParkAvailability();
        executeUpdate(govResponse);
    }

    @Override
    public void convertTo4326() {
        final Iterable<CarParkEntity> carParkEntities = carParkRepository.findAll();

        carParkEntities.forEach(e -> {
            final Wgs84Response response = oneMapService.getWgs84(e.getX(), e.getY());
            e.setLatitude(response.getLatitude());
            e.setLongitude(response.getLongitude());
            carParkRepository.save(e);
        });
    }

    private void executeUpdate(@NotNull final CarParkAvailabilityResponse govResponse) {
        if (govResponse.getItems().isEmpty()) {
            return;
        }

        final Item item = govResponse.getItems().get(0);
        final List<CarParkData> carParkData = item.getCarParkData();
        carParkData.forEach(e -> {
            final CarParkEntity carPark = carParkRepository.findByCarParkNo(e.getNumber());
            if (Objects.isNull(carPark)) {
                return;
            }

            carPark.setAvailability(getAvailability(carPark, e.getCarParkInfo().get(0).getTotalLots(), e.getCarParkInfo().get(0).getAvailableLots()));
            carParkRepository.save(carPark);
        });
    }

    private CarPark convertToCarPark(@NotNull final CarParkEntity e) {
        return CarPark.builder()
                .code(e.getCarParkNo())
                .address(e.getAddress())
                .latitude(e.getLatitude())
                .longitude(e.getLongitude())
                .totalLots(Objects.isNull(e.getAvailability()) ? 0 : e.getAvailability().getTotalLots())
                .availableLots(Objects.isNull(e.getAvailability()) ? 0 :e.getAvailability().getAvailableLots())
                .build();
    }

    private CarParkAvailabilityEntity getAvailability(@NotNull final CarParkEntity carParkEntity,
                                                      @NotNull final Integer totalLots,
                                                      @NotNull final Integer availableLots ) {
        final CarParkAvailabilityEntity availability = carParkEntity.getAvailability();

        if (Objects.isNull(availability)) {
            return new CarParkAvailabilityEntity(totalLots, availableLots);
        } else {
            availability.setTotalLots(totalLots);
            availability.setAvailableLots(availableLots);
        }

        return availability;
    }

}
