import java.io.File;
import java.sql.*;

public class Database {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + new File("csc205.db").getAbsolutePath());
            createTable(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    private static void createTable(Connection conn) throws SQLException {
        String createTable = "" +
                "CREATE TABLE IF NOT EXISTS Time " +
                "( " +
                "programming SMALLINT DEFAULT 0, " +
                "guitar SMALLINT DEFAULT 0" +
                ");";
        Statement stmt = conn.createStatement();
        stmt.execute(createTable);

        String checkRowExists = "SELECT COUNT(*) AS rowCount FROM Time";
        ResultSet rs = stmt.executeQuery(checkRowExists);
        if (rs.next() && rs.getInt("rowCount") == 0) {
            String insertInitialRow = "INSERT INTO Time (programming, guitar) VALUES (0, 0)";
            stmt.execute(insertInitialRow);
        }
    }

    public static void incrementTime(Connection conn, String timeType) {
        String updateSQL = "UPDATE Time SET " + timeType + " = " + timeType + " + 1";

        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating time: " + e.getMessage());
        }
    }

    public static int[] getValues(Connection conn) throws SQLException {
        String getValues = "SELECT * FROM Time";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(getValues);

        return new int[]{ rs.getInt("guitar"), rs.getInt("programming") };
    }
}
