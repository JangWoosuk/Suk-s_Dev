package com.newsEconomy.domain;

import com.newsEconomy.Mapper.NewsMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
@Repository
public class NewsDAOImpl implements NewsDAO {
    @Autowired
    SqlSession sqlSession;
    NewsMapper newsMapper;

    public List<NewsVO> listPaging(Criteria cri) {
        System.out.println(cri.getRowEnd() +","+cri.getRowStart());
        newsMapper = sqlSession.getMapper(NewsMapper.class); //sqlSession객체에서 Mapper클래스를 받아서 newsMapper에 넣습니다.
        List<NewsVO> list = newsMapper.listPaging(cri);     //newsMapper 변수에서 listPaging(cri)에서 수행하고 받아온 기사의 list를 넣어줍니다.
        return list;
    }

    public int listCount() throws Exception {
        newsMapper = sqlSession.getMapper(NewsMapper.class);
        int count = newsMapper.listCount();               //페이지 개수를 구하기 위해서 기사의 수를 구하고 count에 넣어서 반환합니다.
        System.out.println("데이터의 수"+count);
        return count;
    }

    public List<NewsVO> searchList(SearchCriteria scri) {
        newsMapper = sqlSession.getMapper(NewsMapper.class);
        System.out.println(scri.getRowStart()+"," +scri.getRowEnd());
        List<NewsVO> list = newsMapper.searchList(scri);
        return list;
    }

    public int listCount(SearchCriteria scri) throws Exception {
        newsMapper = sqlSession.getMapper(NewsMapper.class);
        int count = newsMapper.searchListCount(scri);
        System.out.println("검색한 데이터의 수"+count);
        return count;
    }

}
