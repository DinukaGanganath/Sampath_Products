package com.sampathproducts.Batch;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.sampathproducts.BatchHasProduct.BatchHasProduct;

@Entity // convert to the Entity class
@Table(name = "batch") // map with purchaseorder table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id", unique = true)
    private Integer batch_id;

    @Column(name = "batch_created_date") // mappling the column "material_added_date" of table "material_details"
    private LocalDateTime batch_created_date;

    @Column(name = "batch_status")
    private Integer batch_status;

    @Column(name = "batch_code")
    private String batch_code;

    @OneToMany(mappedBy = "batch_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BatchHasProduct> batchHasProductList;

}
