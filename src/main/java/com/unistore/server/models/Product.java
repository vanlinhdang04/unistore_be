package com.unistore.server.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    // Primary key?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ProductId;

    @Column(nullable = false, unique = true)
    private String Name;
    private String Catalog;
    private Double Price;
    private String Image;
    private String Description;

    @Column(name = "Os")
    private String OS;
    private String Processer;
    private String Graphics;
    private String Memory;
    private String HardDrive;
    private String Wireless;
    private String Battery;
    private int Quantity; // Number of products in stock
    private int Sold; // Number of products sold
    private String Status;


    public Product( String name, String catalog, Double price, String image, String description, String OS, String processer, String graphics, String memory, String hardDrive, String wireless, String battery, int quantity) {

        Name = name;
        Catalog = catalog;
        Price = price;
        Image = image;
        Description = description;
        this.OS = OS;
        Processer = processer;
        Graphics = graphics;
        Memory = memory;
        HardDrive = hardDrive;
        Wireless = wireless;
        Battery = battery;
        Quantity = quantity;
        Status = "1";
        Sold = 0;
    }

    public Product(Long productId, String name, String catalog, Double price, String image, String description, String OS, String processer, String graphics, String memory, String hardDrive, String wireless, String battery, int quantity, int sold, String status) {
        ProductId = productId;
        Name = name;
        Catalog = catalog;
        Price = price;
        Image = image;
        Description = description;
        this.OS = OS;
        Processer = processer;
        Graphics = graphics;
        Memory = memory;
        HardDrive = hardDrive;
        Wireless = wireless;
        Battery = battery;
        Quantity = quantity;
        Sold = sold;
        Status = status;
    }

    public Product() {

    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCatalog() { return Catalog; }

    public void setCatalog(String catalog) {
        Catalog = catalog;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getSold() {
        return Sold;
    }

    public void setSold(int sold) {
        Sold = sold;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getProcesser() {
        return Processer;
    }

    public void setProcesser(String processer) {
        Processer = processer;
    }

    public String getGraphics() {
        return Graphics;
    }

    public void setGraphics(String graphics) {
        Graphics = graphics;
    }

    public String getMemory() {
        return Memory;
    }

    public void setMemory(String memory) {
        Memory = memory;
    }

    public String getHardDrive() {
        return HardDrive;
    }

    public void setHardDrive(String hardDrive) {
        HardDrive = hardDrive;
    }

    public String getWireless() {
        return Wireless;
    }

    public void setWireless(String wireless) {
        Wireless = wireless;
    }

    public String getBattery() {
        return Battery;
    }

    public void setBattery(String battery) {
        Battery = battery;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", Name='" + Name + '\'' +
                ", Catalog=" + Catalog +
                ", Price=" + Price +
                ", Image='" + Image + '\'' +
                ", Description='" + Description + '\'' +
                ", OS='" + OS + '\'' +
                ", Processer='" + Processer + '\'' +
                ", Graphics='" + Graphics + '\'' +
                ", Memory='" + Memory + '\'' +
                ", HardDrive='" + HardDrive + '\'' +
                ", Wireless='" + Wireless + '\'' +
                ", Battery='" + Battery + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
