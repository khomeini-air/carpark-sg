package com.wego.carpark.core.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "car_park")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarParkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "car_park_no", unique = true)
    private String carParkNo;

    @Column(name = "address")
    private String address;

    @Column(name = "x_coord")
    private Double x;

    @Column(name = "y_coord")
    private Double y;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "car_park_type")
    private String parkType;

    @Column(name = "type_of_parking_system")
    private String parkSystemType;

    @Column(name = "short_term_parking")
    private String shortTermType;

    @Column(name = "free_parking")
    private String freeParking;

    @Column(name = "night_parking")
    private String nightParking;

    @Column(name = "car_park_decks")
    private Integer deckAmount;

    @Column(name = "gantry_height")
    private Double gantryHeight;

    @Column(name = "car_park_basement")
    private String carParkBasement;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CarParkAvailabilityEntity availability;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Timestamp updatedAt;
}
