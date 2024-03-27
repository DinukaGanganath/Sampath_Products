package com.sampathproducts.Vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.sampathproducts.VehicleTypes.VehicleTypes;

@Entity // convert to the Entity class
@Table(name = "vehicle_details") // map with vehicle_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id", unique = true)
    private Integer vehicle_id;

    @Column(name = "vehicle_no")
    private String vehicle_no;

    @Column(name = "vehicle_code")
    private String vehicle_code;

    @Column(name = "vehicle_chasis")
    private String vehicle_chasis;

    @Column(name = "vehicle_engine")
    private String vehicle_engine;

    @Column(name = "vehicle_owner_name")
    private String vehicle_owner_name;

    @Column(name = "vehicle_owner_nic")
    private String vehicle_owner_nic;

    @Column(name = "vehicle_owner_contact")
    private String vehicle_owner_contact;

    @Column(name = "vehicle_last_serviced_date")
    private LocalDateTime vehicle_last_serviced_date;

    @Column(name = "vehicle_licence")
    private String vehicle_licence;

    @Column(name = "created_date_time")
    private LocalDateTime created_date_time;

    @Column(name = "updated_date_time")
    private LocalDateTime updated_date_time;

    @Column(name = "deleted_date_time")
    private LocalDateTime deleted_date_time;

    @Column(name = "vehicle_deleted")
    private Integer vehicle_deleted;

    @OneToOne
    @JoinColumn(name = "vehicle_types_id", referencedColumnName = "vehicle_types_id")
    private VehicleTypes vehicle_types_id;
}
