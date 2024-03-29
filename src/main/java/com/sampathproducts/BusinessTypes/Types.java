package com.sampathproducts.BusinessTypes;

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

@Entity
@Table(name = "business_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Types {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", unique = true)
    @NotNull
    private Integer type_id;

    @Column(name = "type_name")
    @NotNull
    private String type_name;
}
