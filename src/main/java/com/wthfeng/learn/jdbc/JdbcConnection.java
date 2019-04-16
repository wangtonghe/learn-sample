package com.wthfeng.learn.jdbc;

import java.sql.*;

/**
 * jdbc 流程
 *
 * @author wangtonghe
 * @since 2019/3/17 17:34
 */
public class JdbcConnection {

    public static void main(String[] args) {

        try {
            // 1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();


        }
        // 获取连接
        String url = "jdbc:mysql://localhost:3306/sso";
        String user = "root";
        String password = "wangtonghe";


        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String sql = "select *  from sso_user_details limit 0,10";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                System.out.println(resultSet.getObject(1));
                System.out.println(resultSet.getObject(2));
                System.out.println(resultSet.getObject(3));
                System.out.println(resultSet.getObject(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
