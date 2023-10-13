package com.wego.carpark.core.dao.repository;

import com.wego.carpark.core.dao.entity.CarParkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarParkRepository extends JpaRepository<CarParkEntity, Integer> {
    CarParkEntity findByCarParkNo(String carParkNo);

    @Query("SELECT cp FROM CarParkEntity cp WHERE cp.availability > 0 ORDER BY ST_Distance_Sphere(point(cp.longitude, cp.latitude), point(:longitude,:latitude))")
    List<CarParkEntity> findAllAvailable(@Param("latitude") Double latitude, @Param("longitude") Double longitude, Pageable pageable);

}
