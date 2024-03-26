package com.sampathproducts.Customer;

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

@Entity // convert to the Entity class
@Table(name = "customer_details") // map with customer_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true)
    private Integer customer_id;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "customer_nic")
    private String customer_nic;

    @Column(name = "customer_code", unique = true)
    private String customer_code;

    @Column(name = "customer_address_line1")
    private String customer_address_line1;

    @Column(name = "customer_address_city")
    private String customer_address_city;

    @Column(name = "customer_address_postal")
    private String customer_address_postal;

    @Column(name = "customer_business_name")
    private String customer_business_name;

    @Column(name = "customer_business_reg")
    private String customer_business_reg;

    @Column(name = "customer_land_phone")
    private String customer_land_phone;

    @Column(name = "customer_contact_no1")
    private String customer_contact_no1;

    @Column(name = "customer_contact_no2")
    private String customer_contact_no2;

    @Column(name = "customer_email", unique = true)
    private String customer_email;

    @Column(name = "created_date_time")
    private LocalDateTime created_date_time;

    @Column(name = "updated_date_time")
    private LocalDateTime updated_date_time;

    @Column(name = "deleted_date_time")
    private LocalDateTime deleted_date_time;

    @Column(name = "customer_deleted")
    private Integer customer_deleted;

    @OneToOne
    @JoinColumn(name = "customer_area_id", referencedColumnName = "area_id")
    private Area customer_area_id;

    @OneToOne
    @JoinColumn(name = "customer_business_type", referencedColumnName = "type_id")
    private Types customer_business_type;

}
