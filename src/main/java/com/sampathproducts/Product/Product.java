package com.sampathproducts.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

import com.sampathproducts.ProductHasMaterial.ProductHasMaterial;
import com.sampathproducts.ProductSize.ProductSize;
import com.sampathproducts.ProductType.ProductType;

@Entity // convert to the Entity class
@Table(name = "product_details") // map with product_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true) // mapping the column "product_id" of table "product_details"
    private Integer product_id;

    @Column(name = "product_code", unique = true) // mapping the column "product_code" of table "product_details"
    private String product_code;

    @Column(name = "product_usable_time") // mapping the column "product_usable_time" of table "product_details"
    private Integer product_usable_time;

    @Column(name = "product_unit_price") // mapping the column "product_unit_price" of table "product_details"
    private Integer product_unit_price;

    @Column(name = "product_created_date") // mapping the column "product_created_date" of table "product_details"
    private LocalDateTime product_created_date;

    @Column(name = "product_updated_date") // mapping the column "product_updated_date" of table "product_details"
    private LocalDateTime product_updated_date;

    @Column(name = "product_deleted_date") // mapping the column "product_deleted_date" of table "product_details"
    private LocalDateTime product_deleted_date;

    @Column(name = "product_deleted") // mapping the column "product_deleted" of table "product_details"
    private Integer product_deleted;

    @Column(name = "product_has") // mapping the column "product_has" of table "product_details"
    private Integer product_has;

    @Column(name = "product_need") // mapping the column "product_need" of table "product_details"
    private Integer product_need;

    @Column(name = "product_progress") // mapping the column "product_need" of table "product_details"
    private Integer product_progress;

    @Column(name = "product_extra") // mapping the column "product_extra" of table "product_details"
    private Integer product_extra;

    @OneToOne
    @JoinColumn(name = "producttype_id", referencedColumnName = "producttype_id") // mapping from "producttype_details"
                                                                                  // table "producttype_id"
    private ProductType producttype_id;

    @OneToOne
    @JoinColumn(name = "productsize_id", referencedColumnName = "productsize_id") // mapping from "productsize_details"
                                                                                  // table "productsize_id"
    private ProductSize productsize_id;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductHasMaterial> product_has_material_list;
}
