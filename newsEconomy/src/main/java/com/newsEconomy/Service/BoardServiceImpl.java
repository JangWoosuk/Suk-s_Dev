package com.newsEconomy.Service;

import com.newsEconomy.domain.Criteria;
import com.newsEconomy.domain.NewsDAO;
import com.newsEconomy.domain.NewsVO;
import com.newsEconomy.domain.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sun.font.Script;

import javax.inject.Inject;
import java.util.List;
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    NewsDAO nDAO;


    public int listCount() throws Exception {
        return nDAO.listCount();
    }

    public List<NewsVO> listPaging(Criteria cri) {
        return nDAO.listPaging(cri);
    }


    public List<NewsVO> searchList(SearchCriteria scri) throws Exception {
        return nDAO.searchList(scri);
    }

    public int listCount(SearchCriteria scri) throws Exception {
        return nDAO.listCount(scri);
    }
}
