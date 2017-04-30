package common;

import java.sql.*;

/**
 * @author wangtonghe
 * @date 2017/3/11 18:46
 */
public class TestConn {

    public static void main(String[] args) {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/people";
        String user = "wthfeng";
        String password = "wthfeng";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql = "select * from users";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getString("userName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex ){
            ex.printStackTrace();
        }

    }
}
