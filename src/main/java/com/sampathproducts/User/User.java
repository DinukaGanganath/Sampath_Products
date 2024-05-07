package com.sampathproducts.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.sampathproducts.Employee.Employee;

@Entity // convert to the Entity class
@Table(name = "user_details") // map with user_details table
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

    @Column(name = "added_timedate")
    private LocalDateTime added_timedate;

    @Column(name = "note")
    private String note;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee_id;

}
