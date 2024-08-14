package com.sampathproducts.Payment;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // convert to the Entity class
@Table(name = "payment_details") // map with payment_details table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", unique = true) // mapping column
    private Integer payment_id;

    // payment_id int AI PK
    // payment_type varchar(45)
    // payment_amount decimal(10,2)
    // payment_balance decimal(10,2)

    @Column(name = "payment_type") // Mapping to the column of "payment_details" for column name "payment_type"
    private String payment_type;

    @Column(name = "payment_amount") // Mapping to the column of "payment_details" for column name "payment_deleted"
    private Double payment_amount;

    @Column(name = "payment_paid") // Mapping to the column of "payment_paid" for column name "payment_balance"
    private Double payment_paid;

    @Column(name = "payment_discount") // Mapping to the column of "payment_discount" for column name "payment_balance"
    private Integer payment_discount;

    @Column(name = "payment_balance") // Mapping to the column of "payment_balance" for column name "payment_balance"
    private Integer payment_balance;

    @Column(name = "payment_date") // Mapping to the column of "payment_balance" for column name "payment_date"
    private LocalDateTime payment_date;

    @Column(name = "payment_completed") // Mapping to the column of "payment_balance" for column name
                                        // "payment_completed"
    private LocalDateTime payment_completed;

}
