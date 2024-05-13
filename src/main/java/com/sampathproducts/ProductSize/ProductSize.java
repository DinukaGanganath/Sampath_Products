package com.sampathproducts.ProductSize;

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
@Table(name = "productsize_details") // map with material_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productsize_id", unique = true) // mapping column
    private Integer productsize_id;

    @Column(name = "productsize_name")
    private String productsize_name;

    @Column(name = "productsize_deleted")
    private Integer productsize_deleted;

    @Column(name = "productsize_code")
    private String productsize_code;

    @Column(name = "productsize_added_date")
    private LocalDateTime productsize_added_date;

    @Column(name = "productsize_updated_date")
    private LocalDateTime productsize_updated_date;

    @Column(name = "productsize_deleted_date")
    private LocalDateTime productsize_deleted_date;

}
