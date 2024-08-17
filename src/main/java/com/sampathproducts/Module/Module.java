package com.sampathproducts.Module;

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
import java.util.List;

import com.sampathproducts.ModuleRole.ModuleRole;

@Entity
@Table(name = "module")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id", unique = true)
    private Integer module_id;

    @Column(name = "module_name")
    private String module_name;

    @OneToMany(mappedBy = "module_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModuleRole> privilages;
}
