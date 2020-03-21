<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.newsEconomy.domain.NewsVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style type="text/css">
  li {list-style: none; float: left; padding: 6px;}
</style>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>NewsSearching</title>
</head>
<body>
<div style="text-align: center;">
  <h1>News Searching</h1>
  <br>
  <form id="searchForm" role="form" action="/searchList.do" method="GET">
    <label><input type="radio" name="Chk" value="Title">Title</label>
    <label><input type="radio" name="Chk" value="Content">Content</label>
    <input type ="text" id="input_keyword" name="keyword" value="${scri.keyword}" placeholder="searching...">
    <input type ="submit" id="searchBtn" value="검색">
  </form>

</div>
<div>

  <table style="margin-left: auto; margin-right: auto;">
    <c:forEach items="${list}" var = "news">
    <tbody>
    <tr>
      <td>Topics</td>
      <td><c:out value="${news.topics}"/></td>
    </tr>
    <tr>
      <td>Title</td>
      <td>
        <p>
          <a href="#" onClick="window.open('<c:out value="${news.link}"/>','naver',width=1000, height=1500);return false" >
            <c:out value="${news.title}"/></a>
          &emsp;<c:out value="${news.written_time}"/>
          &nbsp;언론사:
          <c:out value="${news.press}"/>
        </p>
      </td>
    </tr>
    <tr>
      <td>Content</td>
      <td><p><c:out value="${news.content}"/></p></td>
    </tr>

    </tbody>
    </c:forEach>
  </table>

  <table style="margin-left: auto; margin-right: auto;">
    <td>
      <c:if test="${pageMaker.prev}"> <!--pageMaker.prev가 true일때-->
        <li><a href="paging${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li> <!--이전 이란 a태그를 만들어준다.-->
      </c:if>

      <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
        <li><a href="paging${pageMaker.makeQuery(idx)}">${idx}</a></li>
      </c:forEach>

      <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
        <li><a href="paging${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
      </c:if>
    </td>

  </table>
</div>
</body>
</html>