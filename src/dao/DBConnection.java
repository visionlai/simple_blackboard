package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnnection {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/blackboard";
    private static final String USER = "root";
    private static final String PASS = "123456";
    private static Connection con=null;

    public static Connection getConnection() {
        try {
            Class.forName(DBDRIVER);
            con = DriverManager.getConnection(DBURL, USER, PASS);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

