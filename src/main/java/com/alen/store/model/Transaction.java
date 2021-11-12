package com.alen.store.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID transaction_id;

    @Column(name = "customer_id")
    private UUID customer_id;
    @Column(name = "product_id")
    private int product_id;
    @Column(name = "subtotal")
    private int subtotal;
    @Column(name = "qty")
    private int qty;
    @Column(name = "date")
    @DateTimeFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private Date date;

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", customer_id=" + customer_id +
                ", product_id=" + product_id +
                ", subtotal=" + subtotal +
                ", qty=" + qty +
                ", date=" + date +
                '}';
    }
}
