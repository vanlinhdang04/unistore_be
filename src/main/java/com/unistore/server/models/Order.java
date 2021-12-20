package com.unistore.server.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    private Long user_id;

    private String name;

    private String status;

    private int quantity;

    private Double total;

    private String address;

    private String purchase_date;

    private String received_date;


    public Order() {}

    public Order(Long user_id, String name, int quantity, Double total, String status, String address, String purchase_date, String received_date) {
        this.user_id = user_id;
        this.name = name;
        this.status = status;
        this.quantity = quantity;
        this.total = total;
        this.address = address;
        this.purchase_date = purchase_date;
        this.received_date = received_date;
    }

    public Long getId() {
        return order_id;
    }

    public void setId(Long id) {
        this.order_id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getReceived_date() {
        return received_date;
    }

    public void setReceived_date(String received_date) {
        this.received_date = received_date;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
