package com.sampathproducts.PurchaseOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

import com.sampathproducts.Request.Request;

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

    @Column(name = "purchase_validity")
    private Integer purchase_validity;

    @Column(name = "purchase_qty")
    private Integer purchase_qty;

    @Column(name = "purchase_price")
    private Double purchase_price;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "quotation_has_purchase_order", joinColumns = @JoinColumn(name = "purchase_order_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
    private Set<Request> requests;

}
