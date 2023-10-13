package com.wego.carpark.core.dao.repository;

import com.wego.carpark.core.dao.entity.CarParkAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarParkAvailabilityRepository extends JpaRepository<CarParkAvailabilityEntity, Integer> {
    /*@Modifying
    @Query("UPDATE CarParkAvailabilityEntity cpa SET cpa.totalLots = :totalLots, cpa.availableLots = :availableLots WHERE cpa.carPark.carParkNo = :carParkNo")
    Integer updateTotalLotsAndAvailableLotsByCarParkNo(
            @Param("totalLots") Integer totalLots,
            @Param("availableLots") Integer availableLots,
            @Param("carParkNo") String carParkNo);*/
}
