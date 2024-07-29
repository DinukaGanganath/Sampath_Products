package com.sampathproducts.CustomerOrderHasProduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sampathproducts.CustomerOrder.CustomerOrder;
import com.sampathproducts.Product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_order_has_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderHasProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_order_product", unique = true)
    private Integer customer_order_product;

    @ManyToOne
    @JoinColumn(name = "customer_order_id", referencedColumnName = "customer_order_id")
    @JsonIgnore
    private CustomerOrder customer_order_id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product_id;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "price")
    @NotNull
    private Double price;

}
