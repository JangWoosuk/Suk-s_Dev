package com.newsEconomy.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Setter
@Getter
public class PagingVO {

    private long amount;

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    private String type;
    private String keyword;

    private long pageNum;

    public long getPageNum() {
        return pageNum;
    }

    public long getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public PagingVO() {
        this(1, 10);
    }

    public PagingVO(long pageNum, long amount) {

        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String[]  getTypeArr() {

        return type == null ? new String[] {} : type.split("");
    }

    public String getListLink() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.getAmount())
                .queryParam("type", this.getType())
                .queryParam("keyword", this.getKeyword());

        return builder.toUriString();
    }


}
