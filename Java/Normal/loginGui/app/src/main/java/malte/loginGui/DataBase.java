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

            try {
                deleteTable(conn);
            } catch (Exception ignored) {

            }
            createTable(conn);

            System.out.println();
            System.out.println("Inserting users");
            insertUser(conn, "Malte", "kode", "Malte Feldthaus");
            insertUser(conn, "test", "kode", "test er");
            insertUser(conn, "test2", "kode", "test2 er");
            insertUser(conn, "test3", "kode", "test3 er");

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

    private static void displayDatabase(Connection conn, String tablename) throws SQLException {
        String selectSQL = "SELECT * FROM " + tablename;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);

        System.out.println("------ " + tablename + " ------");
        while (rs.next()) {
            System.out.println("Full name: " + rs.getString("fullName"));
            System.out.println("Username: " + rs.getString("username"));
            System.out.println("Password: " + rs.getString("password"));
        }
        System.out.println("-------------------------------------");
    }

    private static String[] checkLogin(Connection conn, String tablename, String username, String password) throws SQLException {
        String selectSQL = "SELECT * FROM " + tablename;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);
        String[] str = {"", ""};
        return str;
    }

    private static void insertUser(Connection conn, String username, String password, String fullName) throws SQLException {
        String insertSQL = "INSERT INTO Users(username, password, fullName) VALUES(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSQL);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, fullName);
        pstmt.executeUpdate();
    }

    private static void createTable(Connection conn) throws SQLException {
        String createTable = "" +
                "CREATE TABLE Users " +
                "( " +
                "username varchar(255), " +
                "password varchar(255), " +
                "fullName varchar(255) " +
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
