package com.sampathproducts.Quotation;

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
import java.time.LocalDateTime;

import com.sampathproducts.Material.Material;
import com.sampathproducts.QuotationStatus.QuotationStatus;
import com.sampathproducts.Supplier.Supplier;

@Entity // convert to the Entity class
@Table(name = "quotation_details") // map with quotation_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_id", unique = true) // Mapping from "quotation_details" entity column "quotation_id"
    private Integer quotationid;

    @Column(name = "quotation_code") // Mapping from "quotation_details" entity column "quotation_code"
    private String quotation_code;

    @Column(name = "quotation_created_date") // Mapping from "quotation_details" entity column "quotation_created_date"
    private LocalDateTime quotation_created_date;

    @Column(name = "quotation_requested_date") // Mapping from "quotation_details" entity column
                                               // "quotation_requested_date"
    private LocalDateTime quotation_requested_date;

    @Column(name = "quotation_valid_period") // Mapping from "quotation_details" entity column "quotation_valid_period"
    private Integer quotation_valid_period;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id") // mapping "supplier_id" from "supplier"
    private Supplier supplier_id;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "material_id") // mapping "material_id" from "material"
    private Material material_id;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id") // mapping "status_id" from "status"
    private QuotationStatus status_id;
}
