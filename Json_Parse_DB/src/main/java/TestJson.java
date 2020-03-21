import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

//컬럼명 id, title, content,link,topick,written_time,press,category
public class TestJson {
    //메인 메소드에 있어야 할것.
    //- file 목록
    //jsonparser 객체
    //DB에 넣기
    public static void main(String[] args) throws Exception {

            JsonParse json = new JsonParse();
            File[] files =json.fileList("C:\\Users\\DS\\Documents\\202001");
//            for(int i = 0 ; i<files.length;i++){
//                System.out.println(files[i]);
//            }
            for(int i = 0 ; i<files.length;i++){
                System.out.println(i+"번째 파일입니다.");
                json.jParser(files[i]);
                System.out.println(i+"끝");
            }

    }


        }






