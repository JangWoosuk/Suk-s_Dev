package com.newsEconomy.Service;

import com.newsEconomy.domain.Criteria;
import com.newsEconomy.domain.NewsVO;
import com.newsEconomy.domain.SearchCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BoardService {

    int listCount() throws Exception;
    List<NewsVO> listPaging(Criteria cri);
    public List<NewsVO> searchList(SearchCriteria scri) throws Exception;
    public int listCount(SearchCriteria scri) throws Exception;
}
