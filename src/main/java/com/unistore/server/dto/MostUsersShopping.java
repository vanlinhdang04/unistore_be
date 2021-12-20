package com.unistore.server.dto;

public class MostUsersShopping {
    private Long id;
    private String name;
    private Long count_order;
    private Double total;

    public MostUsersShopping() {}

    public MostUsersShopping(Long id, String name, Long count_order, Double total) {
        this.id = id;
        this.name = name;
        this.count_order = count_order;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount_order() {
        return count_order;
    }

    public void setCount_order(Long count_order) {
        this.count_order = count_order;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
