package com.newsEconomy.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Setter
@Getter
public class PageMaker {

private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 10; //보여줄 페이지의 개수( 1,2,3,4,5,6,7,8,9,10 다음 -> 11,12,13,14,15,16,17,18,19,20 다음)
    private Criteria cri;

    private void calcData() {
        endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum); //cri에 있는 페이지를 10으로 나누어서 소수점 첫째 자리를 반올림 한수 *10을 하면 끝페이지의 수가 나온다.
        startPage = (endPage - displayPageNum) + 1; //만약 endPage가 20 이라면 20-10+1 하며 startPage가 11 이 되어 11,12,13,14,15,16,17,18,19,20 으로 화면에 뿌려줄수있다.

        int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        prev = startPage == 1 ? false : true; //start페이지가 1이면 false가 되어서 이전 이란 버튼이 생성불가
        next = endPage * cri.getPerPageNum() >= totalCount ? false : true; //endPage 와 cri에 있는 perPageNum의 곱이 totalCount 보다 크거나 같으면 next가 false가 되어서 다음 버튼이 나오지 않는다.
    }

    public String makeQuery(int page) {
        //UriComponents 는 uri작성에 도움을 주는 클래스 이며 Builder를 이용해서 uri를 만들수있다.
        //.queryParam에 추가하고 싶은 요소와 값을 넣을수 있다.
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                        .queryParam("page", page)
                        .queryParam("perPageNum", cri.getPerPageNum())
                        .build();

        return uriComponents.toUriString();
    }
    public String makeSearch(int page) {
        //Search에는 추가로 SearchType과 keyword를 넣어서 페이지 이동시에 검색한 내용을 계속 볼수 있습니다.
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                        .queryParam("page", page)
                        .queryParam("perPageNum", cri.getPerPageNum())
                        .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
                        .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
                        .build();
        return uriComponents.toUriString();
    }

    private String encoding(String keyword) {
        //keyword를 UTF-8로 인코딩 하는 작업입니다.

        //keyword가 null값이거나 길이가 0 일때는 바로 반환합니다.
        if(keyword == null || keyword.trim().length() == 0) {
            return "";
        }

        try {
            return URLEncoder.encode(keyword, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            return "";
        }
    }

    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    //DB에서 받아온 전체 데이터의 수를 가져옵니다.
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        //전체 데이터를 가지고 calcData 하여 startPage 와 endPage 를 계산합니다.
        calcData();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public Criteria getCri() {
        return cri;
    }


}