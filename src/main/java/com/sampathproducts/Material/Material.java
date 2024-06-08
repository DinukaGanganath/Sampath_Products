package com.sampathproducts.Material;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity // convert to the Entity class
@Table(name = "material_details") // map with material_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", unique = true) // mappling the column "material_id" of table "material_details"
    private Integer material_id;

    @Column(name = "material_name") // mappling the column "material_name" of table "material_details"
    private String material_name;

    @Column(name = "material_code", unique = true) // mappling the column "material_code" of table "material_details"
    private String material_code;

    @Column(name = "material_deleted", unique = true) // mappling the column "material_deleted" of table
                                                      // "material_details"
    private Integer material_deleted;

    @Column(name = "material_has")
    private Integer material_has;

    @Column(name = "material_want")
    private Integer material_want;

    @Column(name = "material_unit")
    private String material_unit;

    @Column(name = "material_extra")
    private Integer material_extra;

    @Column(name = "material_added_date") // mappling the column "material_added_date" of table "material_details"
    private LocalDateTime material_added_date;

    @Column(name = "material_updated_date") // mappling the column "material_updated_date" of table "material_details"
    private LocalDateTime material_updated_date;

    @Column(name = "material_deleted_date") // mappling the column "material_deleted_date" of table "material_details"
    private LocalDateTime material_deleted_date;

}
