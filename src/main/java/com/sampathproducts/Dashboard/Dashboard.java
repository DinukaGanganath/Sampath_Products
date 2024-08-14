package com.sampathproducts.Dashboard;

import java.time.LocalDateTime;

import com.sampathproducts.Division.Division;

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

@Entity // convert to the Entity class
@Table(name = "dashboard_details") // map with dashboard_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dashboard_id", unique = true) // mapping column
    private Integer dashboard_id;

    @Column(name = "dashboard_name") // Mapping to the column of "dashboard_details" for column name "dashboard_name"
    private String dashboard_name;

    @Column(name = "dashboard_deleted") // Mapping to the column of "dashboard_details" for column name
                                        // "dashboard_deleted"
    private Integer dashboard_deleted;

    @Column(name = "dashboard_code") // Mapping to the column of "dashboard_details" for column name "dashboard_code"
    private String dashboard_code;

    @Column(name = "dashboard_added_date") // Mapping to the column of "dashboard_details" for column name
                                           // "dashboard_added_date"
    private LocalDateTime dashboard_added_date;

    @Column(name = "dashboard_updated_date") // Mapping to the column of "dashboard_details" for column name
                                             // "dashboard_updated_date"
    private LocalDateTime dashboard_updated_date;

    @Column(name = "dashboard_deleted_date") // Mapping to the column of "dashboard_details" for column name
                                             // "dashboard_deleted_date"
    private LocalDateTime dashboard_deleted_date;

    @OneToOne
    @JoinColumn(name = "postal_division_id", referencedColumnName = "postal_division_id") // Mapping to the column of
                                                                                          // "postal_division" for
                                                                                          // column name
                                                                                          // "postal_division_id"
    private Division postal_division_id;

}
