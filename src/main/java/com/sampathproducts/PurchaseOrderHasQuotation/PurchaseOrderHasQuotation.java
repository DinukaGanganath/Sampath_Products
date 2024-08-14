package com.sampathproducts.PurchaseOrderHasQuotation;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sampathproducts.PurchaseOrder.PurchaseOrder;
import com.sampathproducts.Request.Request;

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

@Entity
@Table(name = "purchase_order_has_quotation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderHasQuotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id", unique = true)
    private Integer order_line_id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "purchase_order_id")
    @JsonIgnore
    private PurchaseOrder purchase_order_id;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    private Request request_id;

    @Column(name = "requested_qty")
    private Integer requested_qty;

    @Column(name = "line_price")
    private Double line_price;

    @Column(name = "paid_date")
    private LocalDateTime paid_date;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_paid")
    private Double payment_paid;

    // purchase_order_id int PK
    // request_id int PK
    // requested_qty int
    // line_price decimal(6,2)
}
