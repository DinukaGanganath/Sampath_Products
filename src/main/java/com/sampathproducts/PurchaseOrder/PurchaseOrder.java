package com.sampathproducts.PurchaseOrder;

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

import java.time.LocalDateTime;
import java.util.List;

import com.sampathproducts.PurchaseOrderHasQuotation.PurchaseOrderHasQuotation;

@Entity // convert to the Entity class
@Table(name = "purchase_order") // map with purchaseorder table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id", unique = true)
    private Integer purchase_order_id;

    @Column(name = "purchase_created_date") // mappling the column "material_added_date" of table "material_details"
    private LocalDateTime purchase_created_date;

    @Column(name = "purchase_qty")
    private Integer purchase_qty;

    @Column(name = "purchase_price")
    private Double purchase_price;

    @OneToMany(mappedBy = "purchase_order_id")
    private List<PurchaseOrderHasQuotation> purchaseOrderHasQuotationList;

}
