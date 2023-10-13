package com.wego.carpark.core.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "car_park_availability")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarParkAvailabilityEntity {

    public CarParkAvailabilityEntity(Integer totalLots, Integer availableLots) {
        this.totalLots = totalLots;
        this.availableLots = availableLots;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_lots")
    private Integer totalLots;

    @Column(name = "available_lots")
    private Integer availableLots;

    @OneToOne(mappedBy = "availability")
    private CarParkEntity carPark;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Timestamp updatedAt;
}
