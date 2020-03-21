package com.newsEconomy.Controller;

import com.newsEconomy.Service.BoardService;
import com.newsEconomy.domain.Criteria;
import com.newsEconomy.domain.NewsVO;
import com.newsEconomy.domain.PageMaker;
import com.newsEconomy.domain.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value="/")
    public String index(Model model ,Criteria cri) throws Exception {
        //처음 구동시에 RowStart와 RowEnd를 설정 합니다.
        cri.setRowStart(1);
        cri.setRowEnd(10);
        List<NewsVO> list = boardService.listPaging(cri);     //List를 가져옵니다. 순서: boardServiceimpl -> NewsDaoImpl ->mapper
        cutList(list);                                        //기사가 쓰여진 시간을 보기 쉽게 정리, 기사 내용이 길경우 100자로 줄이는 작업입니다.
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri); //pageMaker에 cri를 설정
        pageMaker.setTotalCount(boardService.listCount());    //가져온 데이터의 총 수를 pageMaker의 totalCount에 넣어서 calcData() 하여 페이지 개수를 정할수 있습니다.
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",pageMaker);
        return "index";                                       //index에 model을 담아서 보냅니다.
    }

    @RequestMapping(value="paging")
    public String pageing(Criteria cri,Model model,@RequestParam("page") int page , @RequestParam("perPageNum") int perPage) throws Exception{
        System.out.println("페이지 눌렀을때 페이지와 펄 페이지"+page+","+perPage);
        cri.setRowEnd(page*10);                             //paging처리를 하기 위해서 rowEnd와 start를 다시 설정합니다.
        cri.setRowStart(cri.getRowEnd()-9);

        List<NewsVO> list = boardService.listPaging(cri);
        cutList(list);
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(boardService.listCount());
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",pageMaker);
        return "index";


    }

  @RequestMapping(value = "/searchList.do" )
    public String searchList(Model model, @RequestParam("Chk") String searchType , @RequestParam("keyword") String keyword ) throws Exception {
        SearchCriteria scri = new SearchCriteria();
        scri.setRowStart(1);                                //scri에 rowStart rowEnd 설정, RequestParam으로 받아온 searchType keyword를 설정해 줍니다.
        scri.setRowEnd(10);
        scri.setKeyword(keyword);
        scri.setSearchType(searchType);
        List<NewsVO> list = boardService.searchList(scri);   //검색한 기사의 List를 가져옵니다. 순서: boardServiceimpl -> NewsDaoImpl ->mapper
        cutList(list);
        model.addAttribute("list" , list);
        PageMaker pageMaker =new PageMaker();

        pageMaker.setCri(scri);
        pageMaker.setTotalCount(boardService.listCount(scri));
        model.addAttribute("pageMaker",pageMaker);
        model.addAttribute("Chk",searchType);  //페이징 하면서 검색 조건을 유지하기 위해 모델에 추가합니다.
        model.addAttribute("keyword",keyword);
        return "searchList";
  }

  @RequestMapping(value="/searchListPaging")
    public String searchListPaging(SearchCriteria scri,Model model, @RequestParam("searchType") String searchType,
                                   @RequestParam("keyword") String keyword ,@RequestParam("page") int page ,
                                   @RequestParam("perPageNum") int perPage) throws Exception{
        System.out.println(searchType +","+keyword+","+page+","+perPage);
        scri.setRowEnd(page*10);
        scri.setRowStart(scri.getRowEnd()-9);
        scri.setSearchType(searchType);
        scri.setKeyword(keyword);
        List<NewsVO> list = boardService.searchList(scri);
        cutList(list);
        PageMaker pageMaker =new PageMaker();
        pageMaker.setCri(scri);
        pageMaker.setTotalCount(boardService.listCount(scri));
        model.addAttribute("list" , list);
        model.addAttribute("pageMaker",pageMaker);
        model.addAttribute("Chk",searchType); //페이징 하면서 검색 조건을 유지하기 위해 모델에 추가합니다.
        model.addAttribute("keyword",keyword);
        return "searchList";
  }

  public List<NewsVO> cutList(List<NewsVO> newsList){
        for(int i = 0 ; i<newsList.size(); i++) {
            if (newsList.get(i).getContent().length() >= 100) //기사 내용이 100글자 이상일 경우(한글은 50자) 기사내용을 100자로 바꿔준다.
                newsList.get(i).setContent(newsList.get(i).getContent().substring(0, 100) + "...");
            //date 뒤에 시간이 나오기 때문에 시간을 자르고 보기 쉬운 형태로 만듭니다 202001011532 -> 2020.01.01
            String date = newsList.get(i).getWritten_time().substring(0, 4) + "." + newsList.get(i).getWritten_time().substring(4, 6) + "." + newsList.get(i).getWritten_time().substring(6, 8);
            newsList.get(i).setWritten_time(date);

        }
        return newsList;
  }

}
