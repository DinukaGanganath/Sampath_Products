package com.sampathproducts.Supplier;

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

import com.sampathproducts.Area.Area;
import com.sampathproducts.BusinessTypes.Types;
import com.sampathproducts.Material.Material;

@Entity // convert to the Entity class
@Table(name = "supplier_details") // map with supplier_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", unique = true) // Mapping from "supplier_details" entity column "supplier_id"
    private Integer supplierid;

    @Column(name = "supplier_name") // Mapping from "supplier_details" entity column "supplier_name"
    private String supplier_name;

    @Column(name = "supplier_nic") // Mapping from "supplier_details" entity column "supplier_nic"
    private String supplier_nic;

    @Column(name = "supplier_code", unique = true) // Mapping from "supplier_details" entity column "supplier_code"
    private String supplier_code;

    @Column(name = "supplier_address_line1") // Mapping from "supplier_details" entity column "supplier_address_line1"
    private String supplier_address_line1;

    @Column(name = "supplier_address_city") // Mapping from "supplier_details" entity column "supplier_address_city"
    private String supplier_address_city;

    @Column(name = "supplier_address_postal") // Mapping from "supplier_details" entity column "supplier_address_postal"
    private String supplier_address_postal;

    @Column(name = "supplier_business_name") // Mapping from "supplier_details" entity column "supplier_business_name"
    private String supplier_business_name;

    @Column(name = "supplier_business_reg") // Mapping from "supplier_details" entity column "supplier_business_reg"
    private String supplier_business_reg;

    @Column(name = "supplier_land_phone") // Mapping from "supplier_details" entity column "supplier_land_phone"
    private String supplier_land_phone;

    @Column(name = "supplier_contact_no1") // Mapping from "supplier_details" entity column "supplier_contact_no1"
    private String supplier_contact_no1;

    @Column(name = "supplier_contact_no2") // Mapping from "supplier_details" entity column "supplier_contact_no2"
    private String supplier_contact_no2;

    @Column(name = "supplier_email", unique = true) // Mapping from "supplier_details" entity column "supplier_email"
    private String supplier_email;

    @Column(name = "created_date_time") // Mapping from "supplier_details" entity column "created_date_time"
    private LocalDateTime created_date_time;

    @Column(name = "updated_date_time") // Mapping from "supplier_details" entity column "updated_date_time"
    private LocalDateTime updated_date_time;

    @Column(name = "deleted_date_time") // Mapping from "supplier_details" entity column "deleted_date_time"
    private LocalDateTime deleted_date_time;

    @Column(name = "supplier_deleted") // Mapping from "supplier_details" entity column "supplier_deleted"
    private Integer supplier_deleted;

    @Column(name = "supplier_agreement") // Mapping from "supplier_details" entity column "supplier_agreement"
    private Integer supplier_agreement;

    @OneToOne
    @JoinColumn(name = "supplier_area_id", referencedColumnName = "area_id") // mapping "area_id" from "area"
    private Area supplier_area_id;

    @OneToOne
    @JoinColumn(name = "supplier_business_type", referencedColumnName = "type_id") // mapping "supplier_business_type"
                                                                                   // from "types"
    private Types supplier_business_type;

    @OneToOne
    @JoinColumn(name = "supplier_material_id", referencedColumnName = "material_id") // mapping "material_id" from
                                                                                     // "material_details"
    private Material supplier_material_id;
}
