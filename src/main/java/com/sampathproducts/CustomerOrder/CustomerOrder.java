package com.sampathproducts.CustomerOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

import com.sampathproducts.Customer.Customer;
import com.sampathproducts.CustomerOrderHasProduct.CustomerOrderHasProduct;

@Entity // convert to the Entity class
@Table(name = "customer_order_details") // map with customer_order_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_order_id", unique = true) // mappling the column "customer_order_id" of table
                                                       // "customer_order_details"
    private Integer customer_order_id;

    @Column(name = "customer_order_code", unique = true) // mappling the column "customer_order_code" of table
    // "customer_order_details"
    private String customer_order_code;

    @Column(name = "customer_order_created") // mappling the column "customer_order_needed" of table
                                             // "customer_order_details"
    private LocalDateTime customer_order_created;

    @Column(name = "customer_order_needed") // mappling the column "customer_order_created" of table
                                            // "customer_order_details"
    private LocalDateTime customer_order_needed;

    @Column(name = "customer_order_status", unique = true) // mappling the column "customer_order_status" of table
    private String customer_order_status;

    @Column(name = "customer_order_total", unique = true) // mappling the column "customer_order_total" of table
    private Double customer_order_total;

    @Column(name = "customer_order_paid", unique = true) // mappling the column "customer_order_pa" of table
    private Double customer_order_paid;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id") // mapping from "customer_details"
                                                                            // table "customer_id"
    private Customer customer_id;

    @OneToMany(mappedBy = "customer_order_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerOrderHasProduct> CustomerOrderHasProductList;
}
