import java.util.ArrayList;

public class Economy {
    //id, title, content,link,topics,written_time,press,category
    String id,title, content,link, written_time,press,category;
    ArrayList<String> topics = new ArrayList<String>();
    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getPress() {
        return press;
    }


    public String getTitle() {
        return title;
    }

    public ArrayList<String> getTopics() {

        return topics;
    }


    public String getWritten_time() {
        return written_time;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPress(String press) {
        this.press = press;
    }



    public void setTitle(String title) {
        this.title = title;
    }


    public void setTopics(String str) {
            String topicsStr=str;
            topics.add(topicsStr);

    }

    public void setWritten_time(String written_time) {
        this.written_time = written_time;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + content + '\'' +
                ", link='" + link + '\'' +
                ", topics='" + topics.toString() + '\'' +
                ", written='" + written_time + '\'' +
                ", press='" + press + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
