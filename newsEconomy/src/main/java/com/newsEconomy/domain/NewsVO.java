package com.newsEconomy.domain;

import lombok.ToString;

@ToString
public class NewsVO {
    //뉴스 기사 객체 입니다.
    int id;
    String title,topics,content,link,written_time,press;
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTopics() {
        return topics;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getWritten_time() {
        return written_time;
    }

    public String getPress() {
        return press;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setWritten_time(String written_time) {
        this.written_time = written_time;
    }

    public void setPress(String press) {
        this.press = press;
    }


}
