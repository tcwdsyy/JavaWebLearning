package syy.servlet_implements.utils;

import java.sql.*;

public class DBUtils {
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // 连接本地数据库
    public static Connection getConnection(){
        Connection connection = THREAD_LOCAL.get();
        if(connection==null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_practice", "root", "123456");
                THREAD_LOCAL.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                THREAD_LOCAL.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void begin(){
        Connection connection = getConnection();
        try{
            connection.setAutoCommit(false);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void commit(){
        Connection connection = getConnection();
        try{
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
//        finally{
//            DBUtil.closeAll(connection,null,null);
//        }
    }

    public static void rollback(){
        Connection connection = getConnection();
        try{
            connection.rollback();
        } catch (SQLException e){
            e.printStackTrace();
        }
//        finally{
//            DBUtil.closeAll(connection,null,null);
//        }
    }
}

