package com.sampathproducts.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

import com.sampathproducts.Employee.Employee;
import com.sampathproducts.Role.Role;

@Entity // convert to the Entity class
@Table(name = "user") // map with user table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Integer user_id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_email", unique = true)
    private String user_email;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "user_photopath")
    private String user_photopath;

    @Column(name = "user_status")
    private Integer user_status;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee_id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_has_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> Roles;

}
