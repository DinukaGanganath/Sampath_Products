package com.sampathproducts.Material;

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
@Table(name = "material_details") // map with material_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", unique = true) // mapping column
    @NotNull
    private Integer material_id;

    @Column(name = "material_name")
    @NotNull
    private String material_name;

    @Column(name = "material_code", unique = true)
    @NotNull
    private String material_code;

    @Column(name = "material_added_date")
    private LocalDateTime material_added_date;

    @Column(name = "material_updated_date")
    private LocalDateTime material_updated_date;

    @Column(name = "material_deleted_date")
    private LocalDateTime material_deleted_date;

}
