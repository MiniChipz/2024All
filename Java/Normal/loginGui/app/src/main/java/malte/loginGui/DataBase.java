package malte.loginGui;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class DataBase {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:csc205.db");
            System.out.println("Database connected!");

            System.out.println();
            System.out.println("Displaying users:");
            displayDatabase(conn, "Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public static Connection databaseConnect() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:sqlite:csc205.db");
        return conn;
    }

    private static void displayDatabase(Connection conn, String tablename) throws SQLException {
        String selectSQL = "SELECT * FROM " + tablename;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);

        System.out.println("------ " + tablename + " ------");
        while (rs.next()) {
            System.out.println("Full name: " + rs.getString("fullName"));
            System.out.println("Username: " + rs.getString("username"));
            System.out.println("Password: " + rs.getString("password"));
            System.out.println("Admin: " + rs.getString("admin"));
        }
        System.out.println("-------------------------------------");
    }

    public static String[] checkLogin(Connection conn, String tablename, String username, String password) throws SQLException {
        String selectSQL = "SELECT * FROM " + tablename;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);

        while(rs.next()) {
            if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
                return new String[] {username, password, rs.getString("fullName"), String.valueOf(rs.getBoolean("admin"))};
            }
        }

        return new String[] {"null"};
    }

    public static boolean doesUserExists(Connection conn, String username) throws SQLException {
        String selectSQL = "SELECT * FROM Users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);

        while(rs.next()) {
            if (rs.getString("username").equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static void insertUser(Connection conn, String username, String password, String fullName, boolean admin) throws SQLException {
        String insertSQL = "INSERT INTO Users(username, password, fullName, admin) VALUES(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSQL);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, fullName);
        pstmt.setBoolean(4, admin);
        pstmt.executeUpdate();
    }

    private static void createTable(Connection conn) throws SQLException {
        String createTable = "" +
                "CREATE TABLE Users " +
                "( " +
                "username varchar(10), " +
                "password varchar(64), " +
                "fullName varchar(100), " +
                "admin BOOLEAN" +
                "); " +
                "";
        Statement stmt = conn.createStatement();
        stmt.execute(createTable);
    }

    private static void deleteTable(Connection conn) throws SQLException {
        String deleteTable = "DROP TABLE Users";
        Statement stmt = conn.createStatement();
        stmt.execute(deleteTable);
    }
}
