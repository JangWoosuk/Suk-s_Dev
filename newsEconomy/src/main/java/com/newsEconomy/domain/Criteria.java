package com.newsEconomy.domain;

//Page에 기준을 잡기 위한 객체입니다.
public class Criteria {
    private int page;
    private int perPageNum;
    private int rowStart;
    private int rowEnd;

    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public int getRowStart() {
        return rowStart;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public int getPage() {
        return page;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public void setRowEnd(int rowEnd) {
        this.rowEnd = rowEnd;
    }

    public void setPage(int page) {
        //page가 0이거나 그보다 작으면 page에 1을 넣고 리턴합니다.
        if (page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setPerPageNum(int perPageNum) {
        //perPageNum이 0보다 크거나 같거나 100보다 작으면 perPageNum은 10 이 들어갑니다.
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }
}

