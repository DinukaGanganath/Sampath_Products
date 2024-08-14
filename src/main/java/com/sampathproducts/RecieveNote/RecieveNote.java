package com.sampathproducts.RecieveNote;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.sampathproducts.Payment.Payment;
import com.sampathproducts.Request.Request;

@Entity // convert to the Entity class
@Table(name = "recieve_note") // map with recieve_note table
@Data // for getters and setters
@NoArgsConstructor // default constructor
@AllArgsConstructor // all argument constructor
public class RecieveNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recieve_note_id", unique = true)
    private Integer recieve_note_id;

    @Column(name = "recieve_note_date") // mappling the column "material_added_date" of table "material_details"
    private LocalDateTime recieve_note_date;

    @Column(name = "recieve_note_code")
    private String recieve_note_code;

    @Column(name = "recieve_note_qty")
    private Integer recieve_note_qty;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    private Payment payment_id;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    private Request request_id;

}
