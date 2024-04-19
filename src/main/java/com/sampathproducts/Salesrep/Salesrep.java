package com.sampathproducts.Salesrep;

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
@Table(name = "salesrep_details") // map with salesrep_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Salesrep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salesrep_id", unique = true)
    private Integer salesrep_id;

    @Column(name = "salesrep_code")
    private String salesrep_code;

    @Column(name = "salesrep_name")
    private String salesrep_name;

    @Column(name = "salesrep_nic")
    private String salesrep_nic;

    @Column(name = "salesrep_address_line1")
    private String salesrep_address_line1;

    @Column(name = "salesrep_address_city")
    private String salesrep_address_city;

    @Column(name = "salesrep_land_no")
    private String salesrep_land_no;

    @Column(name = "salesrep_contact_no1")
    private String salesrep_contact_no1;

    @Column(name = "salesrep_contact_no2")
    private String salesrep_contact_no2;

    @Column(name = "salesrep_email")
    private String salesrep_email;

    @Column(name = "salesrep_gender")
    private String salesrep_gender;

    @Column(name = "salesrep_birthdate")
    private LocalDateTime salesrep_birthdate;

    @Column(name = "salesrep_address_postal")
    private String salesrep_address_postal;

    @Column(name = "created_date_time")
    private LocalDateTime created_date_time;

    @Column(name = "updated_date_time")
    private LocalDateTime updated_date_time;

    @Column(name = "deleted_date_time")
    private LocalDateTime deleted_date_time;

    @Column(name = "salesrep_deleted")
    private Integer salesrep_deleted;

    @OneToOne
    @JoinColumn(name = "salesrep_address_area_id", referencedColumnName = "area_id")
    private Area salesrep_address_area_id;

}
