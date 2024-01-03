package com.sampathproducts.Supplier;

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
import java.time.LocalDateTime;

@Entity // convert to the Entity class
@Table(name = "supplier_details") // map with supplier_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", unique = true)
    @NotNull
    private Integer supplier_id;

    @Column(name = "supplier_name")
    @NotNull
    private String supplier_name;

    @Column(name = "supplier_code", unique = true)
    @NotNull
    private String supplier_code;

    @Column(name = "supplier_address_line_one")
    @NotNull
    private String supplier_address_line_one;

    @Column(name = "supplier_address_line_two")
    private String supplier_address_line_two;

    @Column(name = "supplier_address_city")
    private String supplier_address_city;

    @Column(name = "supplier_address_postal")
    private String supplier_address_postal;

    @Column(name = "supplier_business_name")
    private String supplier_business_name;

    @Column(name = "supplier_contact_no_one")
    @NotNull
    private String supplier_contact_no_one;

    @Column(name = "supplier_contact_no_two")
    private String supplier_contact_no_two;

    @Column(name = "supplier_email", unique = true)
    @NotNull
    private String supplier_email;

    @Column(name = "created_date_time")
    private LocalDateTime created_date_time;

    @Column(name = "updated_date_time")
    private LocalDateTime updated_date_time;

    @Column(name = "deleted_date_time")
    private LocalDateTime deleted_date_time;

}
