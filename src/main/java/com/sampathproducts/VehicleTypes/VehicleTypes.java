package com.sampathproducts.VehicleTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_types_id", unique = true)
    @NotNull
    private Integer vehicle_types_id;

    @Column(name = "vehicle_types_code")
    @NotNull
    private String vehicle_types_code;

    @Column(name = "vehicle_types_description")
    @NotNull
    private String vehicle_types_description;
}
