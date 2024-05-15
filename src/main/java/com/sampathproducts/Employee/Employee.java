package com.sampathproducts.Employee;

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

import com.sampathproducts.Area.Area;

@Entity // convert to the Entity class
@Table(name = "employee_details") // map with employee_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", unique = true) // mapping is created the entity "employee_details" column name
                                                 // "employee_id"
    private Integer employee_id;

    @Column(name = "employee_code") // mapping is created the entity "employee_details" column name "employee_code"
    private String employee_code;

    @Column(name = "employee_name") // mapping is created the entity "employee_details" column name "employee_name"
    private String employee_name;

    @Column(name = "employee_nic") // mapping is created the entity "employee_details" column name "employee_nic"
    private String employee_nic;

    @Column(name = "employee_address_line1") // mapping is created the entity "employee_details" column name
                                             // "employee_address_line1"
    private String employee_address_line1;

    @Column(name = "employee_address_city") // mapping is created the entity "employee_details" column name
                                            // "employee_address_city"
    private String employee_address_city;

    @Column(name = "employee_land_no") // mapping is created the entity "employee_details" column name
                                       // "employee_land_no"
    private String employee_land_no;

    @Column(name = "employee_contact_no1") // mapping is created the entity "employee_details" column name
                                           // "employee_contact_no1"
    private String employee_contact_no1;

    @Column(name = "employee_contact_no2") // mapping is created the entity "employee_details" column name
                                           // "employee_contact_no2"
    private String employee_contact_no2;

    @Column(name = "employee_email") // mapping is created the entity "employee_details" column name "employee_email
    private String employee_email;

    @Column(name = "employee_gender") // mapping is created the entity "employee_details" column name
                                      // "employee_gender"
    private String employee_gender;

    @Column(name = "employee_birthdate") // mapping is created the entity "employee_details" column name
                                         // "employee_birthdate"
    private LocalDateTime employee_birthdate;

    @Column(name = "employee_address_postal") // mapping is created the entity "employee_details" column name
                                              // "employee_address_postal"
    private String employee_address_postal;

    @Column(name = "created_date_time") // mapping is created the entity "employee_details" column name
                                        // "created_date_time"
    private LocalDateTime created_date_time;

    @Column(name = "updated_date_time") // mapping is created the entity "employee_details" column name
                                        // "updated_date_time"
    private LocalDateTime updated_date_time;

    @Column(name = "deleted_date_time") // mapping is created the entity "employee_details" column name
                                        // "deleted_date_time"
    private LocalDateTime deleted_date_time;

    @Column(name = "employee_deleted") // mapping is created the entity "employee_details" column name
                                       // "employee_deleted"
    private Integer employee_deleted;

    @OneToOne
    @JoinColumn(name = "employee_address_area_id", referencedColumnName = "area_id") // mapping "area_details" entity
                                                                                     // with column "area_id"
    private Area employee_address_area_id;

}
