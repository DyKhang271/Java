package week6_connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection con = null;

    public static Connection getConnect() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost;databaseName=QLNhanVien;encrypt=false;trustServerCertificate=true";
            String user = "sa";
            String password = "123456";

            con = DriverManager.getConnection(url, user, password);
        }
        return con;
    }

    public static void disconnect() {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con = null;
            }
        }
    }
}