package com.sampathproducts.BatchHasProduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sampathproducts.Batch.Batch;
import com.sampathproducts.Product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "batch_has_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchHasProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchproduct", unique = true)
    private Integer batchproduct;

    @ManyToOne
    @JoinColumn(name = "batch_id", referencedColumnName = "batch_id")
    @JsonIgnore
    private Batch batch_id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product_id;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "batch_product_created")
    private LocalDateTime batch_product_created;

    @Column(name = "batch_product_expired")
    private LocalDateTime batch_product_expired;

}
