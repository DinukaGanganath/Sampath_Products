package com.sampathproducts.SalesrepHasVehicleTypes;

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

import com.sampathproducts.Salesrep.Salesrep;
import com.sampathproducts.VehicleTypes.VehicleTypes;

@Entity // convert to the Entity class
@Table(name = "salesrep_has_vehicle_types_details") // map with salesrepHasVehicleTypes_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class SalesrepHasVehicleTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salesrep_vehicle_id", unique = true)
    private Integer salesrep_vehicle_id;

    @OneToOne
    @JoinColumn(name = "salesrep_details_salesrep_id", referencedColumnName = "salesrep_id")
    private Salesrep salesrep_details_salesrep_id;

    @OneToOne
    @JoinColumn(name = "vehicle_types_vehicle_types_id", referencedColumnName = "vehicle_types_id")
    private VehicleTypes vehicle_types_vehicle_types_id;

    @Column(name = "salesrep_has_vehicle_deleted")
    private Integer salesrep_has_vehicle_deleted;

}
