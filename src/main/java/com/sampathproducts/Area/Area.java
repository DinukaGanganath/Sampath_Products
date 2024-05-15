package com.sampathproducts.Area;

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
@Table(name = "area_details") // map with area_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id", unique = true) // mapping column
    private Integer area_id;

    @Column(name = "area_name") // Mapping to the column of "area_details" for column name "area_name"
    private String area_name;

    @Column(name = "area_deleted") // Mapping to the column of "area_details" for column name "area_deleted"
    private Integer area_deleted;

    @Column(name = "area_code") // Mapping to the column of "area_details" for column name "area_code"
    private String area_code;

    @Column(name = "area_added_date") // Mapping to the column of "area_details" for column name "area_added_date"
    private LocalDateTime area_added_date;

    @Column(name = "area_updated_date") // Mapping to the column of "area_details" for column name "area_updated_date"
    private LocalDateTime area_updated_date;

    @Column(name = "area_deleted_date") // Mapping to the column of "area_details" for column name "area_deleted_date"
    private LocalDateTime area_deleted_date;

    @OneToOne
    @JoinColumn(name = "postal_division_id", referencedColumnName = "postal_division_id") // Mapping to the column of
                                                                                          // "postal_division" for
                                                                                          // column name
                                                                                          // "postal_division_id"
    private Division postal_division_id;

}
