package com.sampathproducts.ProductType;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // convert to the Entity class
@Table(name = "producttype_details") // map with material_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producttype_id", unique = true) // mapping column
    private Integer producttype_id;

    @Column(name = "producttype_name")
    private String producttype_name;

    @Column(name = "producttype_deleted")
    private Integer producttype_deleted;

    @Column(name = "producttype_code")
    private String producttype_code;

    @Column(name = "producttype_added_date")
    private LocalDateTime producttype_added_date;

    @Column(name = "producttype_updated_date")
    private LocalDateTime producttype_updated_date;

    @Column(name = "producttype_deleted_date")
    private LocalDateTime producttype_deleted_date;

}
