package com.unistore.server.dto;

import org.json.simple.JSONArray;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class OrderRequest {
    private String name;

    private String address;

    private JSONArray product;

    private int quantity;

    private Double total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public JSONArray getProduct() {
        return product;
    }

    public void setProduct(JSONArray product) {
        this.product = product;
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
}
