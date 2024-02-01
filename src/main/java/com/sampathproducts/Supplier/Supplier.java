package com.sampathproducts.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.sampathproducts.Area.Area;
import com.sampathproducts.BusinessTypes.Types;

@Entity // convert to the Entity class
@Table(name = "supplier_details") // map with supplier_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", unique = true)
    private Integer supplier_id;

    @Column(name = "supplier_name")
    @NotNull
    private String supplier_name;

    @Column(name = "supplier_code", unique = true)
    @NotNull
    private String supplier_code;

    @Column(name = "supplier_address_line1")
    @NotNull
    private String supplier_address_line1;

    @Column(name = "supplier_address_city")
    private String supplier_address_city;

    @Column(name = "supplier_address_postal")
    private String supplier_address_postal;

    @Column(name = "supplier_business_name")
    private String supplier_business_name;

    @Column(name = "supplier_contact_no1")
    @NotNull
    private String supplier_contact_no1;

    @Column(name = "supplier_contact_no2")
    private String supplier_contact_no2;

    @Column(name = "supplier_email", unique = true)
    @NotNull
    private String supplier_email;

    @Column(name = "created_date_time")
    private LocalDateTime created_date_time;

    @Column(name = "updated_date_time")
    private LocalDateTime updated_date_time;

    @Column(name = "deleted_date_time")
    private LocalDateTime deleted_date_time;

    @OneToOne
    @JoinColumn(name = "supplier_area_id", referencedColumnName = "area_id")
    private Area supplier_area_id;

    @OneToOne
    @JoinColumn(name = "supplier_business_type", referencedColumnName = "type_id")
    private Types supplier_business_type;
}
