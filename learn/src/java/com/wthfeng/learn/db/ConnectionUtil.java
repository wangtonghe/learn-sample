package com.wthfeng.learn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wangtonghe
 * @date 2017/12/19 11:44
 */
public class ConnectionUtil {

    public static  Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/people";
            String user = "root";
            String pwd = "wangtonghe";
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;

    }


}
