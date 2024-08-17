package com.sampathproducts.ModuleRole;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sampathproducts.Module.Module;
import com.sampathproducts.Role.Role;

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

@Entity // convert to the Entity class
@Table(name = "module_has_role") // map with material_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class ModuleRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_role", unique = true) // mapping column
    private Integer module_role;

    @Column(name = "sel")
    private Integer sel;

    @Column(name = "upd")
    private Integer upd;

    @Column(name = "cre")
    private Integer cre;

    @Column(name = "del")
    private Integer del;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    @JsonIgnore
    private Module module_id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role_id;

}