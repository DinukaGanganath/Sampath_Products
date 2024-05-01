package com.sampathproducts.Product;

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
@Table(name = "product_details") // map with product_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    @NotNull
    private Integer product_id;

    @Column(name = "product_name")
    @NotNull
    private String product_name;

    @Column(name = "product_code", unique = true)
    @NotNull
    private String product_code;

    @Column(name = "product_usable_time")
    private Integer product_usable_time;

    @Column(name = "product_unit_price")
    @NotNull
    private Double product_unit_price;

    @Column(name = "product_unit_quantity")
    @NotNull
    private String product_unit_quantity;

    @Column(name = "product_created_date")
    private LocalDateTime product_created_date;

    @Column(name = "product_updated_date")
    private LocalDateTime product_updated_date;

    @Column(name = "Product_deleted_date")
    private LocalDateTime Product_deleted_date;

    @Column(name = "Product_deleted")
    private Integer Product_deleted;
}
