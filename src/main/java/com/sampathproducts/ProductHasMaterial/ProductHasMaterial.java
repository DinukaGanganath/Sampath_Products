package com.sampathproducts.ProductHasMaterial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sampathproducts.Material.Material;
import com.sampathproducts.Product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_material_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductHasMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_material_id", unique = true) // mapping "product_material_details" table column
                                                         // "product_material_id"
    private Integer product_material_id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id") // mapping "product_material_details" table
                                                                          // "column produt_id"
    @JsonIgnore
    private Product product_id;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "material_id") // mapping "product_material_details" table
                                                                            // column "material_id"
    private Material material_id;

    @Column(name = "quantity_needed") // mapping "product_material_details" table column "quantity_needed"
    @NotNull
    private Double quantity_needed;

}
