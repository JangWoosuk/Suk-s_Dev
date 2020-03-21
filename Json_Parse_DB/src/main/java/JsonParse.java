import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class JsonParse {

    String inline = "";
    int count = 0;

    public File[] fileList(String path) {
        File file = new File(path);
        File[] fileList = file.listFiles();

        return fileList;
    }
    //파일을 매개변수로 받아서 그 파일을 읽고 news 객체에 넣어주고 DB접속까지.
    public void jParser(File file) throws Exception {
        try {
            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(fileReader);
            inline = null;


            while ((inline = bufReader.readLine()) != null) {
                count++;

                String[] JArr = inline.split("}");
                if(JArr.length==1){
                    Gson gson = new Gson();
                    Economy news = gson.fromJson(inline,Economy.class);

                    //여기서 new가 비었는데 출력을 하려니까 계속 nullException이 떳기에 조건문 추가
                    if(news==null){
                        System.out.println("new가 null입니다.");
                        continue;
                    }
                    System.out.println(news.toString());
                    MariaDB maria = new MariaDB();
                    maria.insert(news);

                }
                else{
                    System.out.println("여기는 하나의 json에 두개의 json이 들어갔습니다.");
                    for(int i = 0 ; i<JArr.length;i++){
                        //}를 기준으로 짤랐기에 String맨뒤에 }가 짤려서 들어갔기에 }를 붙혀준다.
                        JArr[i] += "}";
                        Gson gson = new Gson();
                        Economy news = gson.fromJson(JArr[i],Economy.class);
                        System.out.println(news.toString());
                        MariaDB maria = new MariaDB();
                        maria.insert(news);
                    }

                }
            }
            System.out.println(count);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
