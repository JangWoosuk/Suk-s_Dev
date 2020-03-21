package com.newsEconomy.domain;

import java.util.List;

public interface NewsDAO {
    public List<NewsVO> listPaging(Criteria cri);
    public int listCount() throws Exception;
    public List<NewsVO> searchList(SearchCriteria scri) throws Exception;
    public int listCount(SearchCriteria scri) throws Exception;

}
