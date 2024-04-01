package com.sampathproducts.Privilage;

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
@Table(name = "privilage") // map with material_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Privilage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilage_id", unique = true) // mapping column
    private Integer privilage_id;

    @Column(name = "sel")
    private Integer sel;

    @Column(name = "edit")
    private Integer edit;

    @Column(name = "cre")
    private Integer cre;

    @Column(name = "del")
    private Integer del;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    private Module module_id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role_id;

}