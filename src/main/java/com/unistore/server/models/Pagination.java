package com.unistore.server.models;

public class Pagination {
    private int page;
    private int totalPage;
    private int totolRow;

    public Pagination() {}

    public Pagination(int page, int totalPage, int totolRow) {
        this.page = page;
        this.totalPage = totalPage;
        this.totolRow = totolRow;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotolRow() {
        return totolRow;
    }

    public void setTotolRow(int totolRow) {
        this.totolRow = totolRow;
    }
}
