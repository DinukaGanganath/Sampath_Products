package com.sampathproducts.Customer;

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
import com.sampathproducts.BusinessTypes.Types;

@Entity // convert to the Entity class
@Table(name = "customer_details") // map with customer_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true) // mapping the column with the "customer_details" table column
                                                 // "customer_id"
    private Integer customer_id;

    @Column(name = "customer_name") // mapping the column with the "customer_details" table column "customer_name"
    private String customer_name;

    @Column(name = "customer_nic") // mapping the column with the "customer_details" table column "customer_nic"
    private String customer_nic;

    @Column(name = "customer_code", unique = true) // mapping the column with the "customer_details" table column
                                                   // "customer_code"
    private String customer_code;

    @Column(name = "customer_address_line1") // mapping the column with the "customer_details" table column
                                             // "customer_address_line1"
    private String customer_address_line1;

    @Column(name = "customer_address_city") // mapping the column with the "customer_details" table column
                                            // "customer_address_city"
    private String customer_address_city;

    @Column(name = "customer_address_postal") // mapping the column with the "customer_details" table column
                                              // "customer_address_postal"
    private String customer_address_postal;

    @Column(name = "customer_business_name") // mapping the column with the "customer_details" table column
                                             // "customer_business_name"
    private String customer_business_name;

    @Column(name = "customer_business_reg") // mapping the column with the "customer_details" table column
                                            // "customer_business_reg"
    private String customer_business_reg;

    @Column(name = "customer_land_phone") // mapping the column with the "customer_details" table column
                                          // "customer_land_phone"
    private String customer_land_phone;

    @Column(name = "customer_contact_no1") // mapping the column with the "customer_details" table column
                                           // "customer_contact_no1"
    private String customer_contact_no1;

    @Column(name = "customer_contact_no2") // mapping the column with the "customer_details" table column
                                           // "customer_contact_no2"
    private String customer_contact_no2;

    @Column(name = "customer_email", unique = true) // mapping the column with the "customer_details" table column
                                                    // "customer_email"
    private String customer_email;

    @Column(name = "created_date_time") // mapping the column with the "customer_details" table column
                                        // "created_date_time"
    private LocalDateTime created_date_time;

    @Column(name = "updated_date_time") // mapping the column with the "customer_details" table column
                                        // "updated_date_time"
    private LocalDateTime updated_date_time;

    @Column(name = "deleted_date_time") // mapping the column with the "customer_details" table column
                                        // "deleted_date_time"
    private LocalDateTime deleted_date_time;

    @Column(name = "customer_deleted") // mapping the column with the "customer_details" table column
                                       // "customer_deleted"
    private Integer customer_deleted;

    @OneToOne
    @JoinColumn(name = "customer_area_id", referencedColumnName = "area_id") // mapping the column with the
                                                                             // "area_details" table column "area_id"
    private Area customer_area_id;

    @OneToOne
    @JoinColumn(name = "customer_business_type", referencedColumnName = "type_id") // mapping the column with the
                                                                                   // "business_type" table column
                                                                                   // "type_id"
    private Types customer_business_type;

}
