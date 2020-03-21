import java.sql.*;

public class MariaDB {
    String JDBC_Driver = "org.mariadb.jdbc.Driver";
    String DB_URL = "jdbc:mariadb:localhost";
    String userName = "@@@@";
    String userPassword = "@@@@";
    Connection conn = null;
    PreparedStatement pstst = null;
    //DB에 연결하는 메소드
    public Connection connectDB(String Driver, String DB_URL, String UserName, String UserPassword) {
        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(DB_URL, UserName, UserPassword);
            if (conn != null) {
                System.out.println("DB접속 성공!!");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("클래스파일 드라이버 찾기 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB접속 실패");
            e.printStackTrace();
        }
        return conn;
    }

    //DB삽입 메소드
    public void insert (Economy eco){

        PreparedStatement pstate = null;

        try{


            conn = connectDB(JDBC_Driver,DB_URL,userName,userPassword);
            conn.setAutoCommit(false);
            String sql = "insert into nwj.newsdata(title, content,link,topics,written_time,press,category) values(?,?,?,?,?,?,?)";

            pstate = conn.prepareStatement(sql);
            pstate .setString(1,eco.getTitle());
            pstate.setString(2,eco.getContent());
            pstate.setString(3,eco.getLink());
            pstate.setString(4,eco.getTopics().toString());
            pstate.setString(5,eco.getWritten_time());
            pstate.setString(6,eco.getPress());
            pstate.setString(7,eco.getCategory());

            int row = pstate.executeUpdate();
            if(row==1){
                System.out.println("DB삽입 성공!");
                conn.commit();
            }
            else{
                System.out.println("DB삽입 실패!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(pstate!=null){
                try{
                    pstate.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if(conn != null){
                try{
                    conn.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
