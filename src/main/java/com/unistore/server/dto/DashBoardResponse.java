package com.unistore.server.dto;

public class DashBoardResponse {
    long countUser;
    long countProduct;
    long countOrder;
    long countOutOfStock;
    long countUsersBlock;
    double totalMoney;
    int countAdminMod;
    int countProductNo;
    int countOrdersCancel;

    public DashBoardResponse(long countUser, long countProduct, long countOrder, long countOutOfStock, long countUsersBlock, double totalMoney, int countAdminMod, int countProductNo, int countOrdersCancel) {
        this.countUser = countUser;
        this.countProduct = countProduct;
        this.countOrder = countOrder;
        this.countOutOfStock = countOutOfStock;
        this.countUsersBlock = countUsersBlock;
        this.totalMoney = totalMoney;
        this.countAdminMod = countAdminMod;
        this.countProductNo = countProductNo;
        this.countOrdersCancel = countOrdersCancel;
    }

    public long getCountUser() {
        return countUser;
    }

    public void setCountUser(long countUser) {
        this.countUser = countUser;
    }

    public long getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(long countProduct) {
        this.countProduct = countProduct;
    }

    public long getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(long countOrder) {
        this.countOrder = countOrder;
    }

    public long getCountOutOfStock() {
        return countOutOfStock;
    }

    public void setCountOutOfStock(long countOutOfStock) {
        this.countOutOfStock = countOutOfStock;
    }

    public long getCountUsersBlock() {
        return countUsersBlock;
    }

    public void setCountUsersBlock(long countUsersBlock) {
        this.countUsersBlock = countUsersBlock;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getCountAdminMod() {
        return countAdminMod;
    }

    public void setCountAdminMod(int countAdminMod) {
        this.countAdminMod = countAdminMod;
    }

    public int getCountProductNo() {
        return countProductNo;
    }

    public void setCountProductNo(int countProductNo) {
        this.countProductNo = countProductNo;
    }

    public int getCountOrdersCancel() {
        return countOrdersCancel;
    }

    public void setCountOrdersCancel(int countOrdersCancel) {
        this.countOrdersCancel = countOrdersCancel;
    }
}
