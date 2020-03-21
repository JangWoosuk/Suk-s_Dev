package com.newsEconomy.Mapper;

import com.newsEconomy.domain.Criteria;
import com.newsEconomy.domain.NewsVO;
import com.newsEconomy.domain.SearchCriteria;

import java.util.List;
//resources/Mappers/mapper.xml에서 사용할 메소드가 정의 되어 있습니다.
public interface NewsMapper {
    List<NewsVO> listPaging(Criteria cri);
    List<NewsVO> searchList(SearchCriteria scri);
    int listCount();
    int searchListCount(SearchCriteria scri);
}
