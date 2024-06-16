package com.sampathproducts.QuotationRequest;

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

import com.sampathproducts.Supplier.Supplier;

@Entity // convert to the Entity class
@Table(name = "request_quotation") // map with request_quotation table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class QuotationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", unique = true) // mappling the column "request_id" of table "request_quotation"
    private Integer request_id;

    @Column(name = "request_code", unique = true) // mappling the column "request_code" of table "request_quotation"
    private String request_code;

    @Column(name = "request_created") // mappling the column "request_created" of table
                                      // "request_quotation"
    private Integer request_created;

    @Column(name = "request_deleted") // mappling the column "request_deleted" of table
                                      // "request_quotation"
    private Integer request_deleted;

    @Column(name = "request_date") // mappling the column "request_date" of table "request_quotation"
    private LocalDateTime request_date;

    @OneToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id") // mapping from "supplier_details"
                                                                            // table "supplier_id"
    private Supplier supplier_id;

    @Column(name = "request_units", unique = true) // mappling the column "request_units" of table
                                                   // "request_quotation"
    private Integer request_units;

    @Column(name = "request_price", unique = true) // mappling the column "request_price" of table
                                                   // "request_quotation"
    private Integer request_price;

    @Column(name = "request_validity", unique = true) // mappling the column "request_validity" of table
                                                      // "request_quotation"
    private Integer request_validity;

    @Column(name = "request_created_date") // mappling the column "request_created_date" of table "request_quotation"
    private LocalDateTime request_created_date;

}
