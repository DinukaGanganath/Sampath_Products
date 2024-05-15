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
    @Column(name = "productsize_id", unique = true) // mapping "productsize_id" column with the table
                                                    // "productsize_details"
    private Integer productsize_id;

    @Column(name = "productsize_name") // mapping "productsize_name" column with the table "productsize_details"
    private String productsize_name;

    @Column(name = "productsize_deleted") // mapping "productsize_deleted" column with the table "productsize_details"
    private Integer productsize_deleted;

    @Column(name = "productsize_code") // mapping "productsize_code" column with the table "productsize_details"
    private String productsize_code;

    @Column(name = "productsize_added_date") // mapping "productsize_added_date" column with the table
                                             // "productsize_details"
    private LocalDateTime productsize_added_date;

    @Column(name = "productsize_updated_date") // mapping "productsize_updated_date" column with the table
                                               // "productsize_details"
    private LocalDateTime productsize_updated_date;

    @Column(name = "productsize_deleted_date") // mapping "productsize_deleted_date" column with the table
                                               // "productsize_details"
    private LocalDateTime productsize_deleted_date;

}
