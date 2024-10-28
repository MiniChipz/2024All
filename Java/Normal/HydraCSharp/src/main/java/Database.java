import java.sql.*;

public class Database {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:csc205.db");
            System.out.println("Database connected");
            try {
                deleteTable(conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            createTable(conn);
            displayDatabase(conn);
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

    private static void displayDatabase(Connection conn) throws SQLException {
        String selectSQL = "SELECT * FROM Time";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);

        System.out.println("------ Times ------");
        System.out.println("Programming: " + rs.getInt("programming"));
        System.out.println("Guitar: " + rs.getInt("guitar"));
        System.out.println("-------------------");
    }

    private static void createTable(Connection conn) throws SQLException {
        String createTable = "" +
                "CREATE TABLE Time " +
                "( " +
                "programming SMALLINT DEFAULT 0, " +
                "guitar SMALLINT DEFAULT 0" +
                ");";
        Statement stmt = conn.createStatement();
        stmt.execute(createTable);

        // Insert initial row
        String insertInitialRow = "INSERT INTO Time (programming, guitar) VALUES (0, 0)";
        stmt.execute(insertInitialRow);
    }

    private static void deleteTable(Connection conn) throws SQLException {
        String deleteTable = "DROP TABLE Time";
        Statement stmt = conn.createStatement();
        stmt.execute(deleteTable);
    }

    public static void incrementTime(Connection conn, String timeType) {
        // Validate timeType input to avoid SQL injection
        if (!timeType.equals("guitar") && !timeType.equals("programming")) {
            throw new IllegalArgumentException("Invalid time type. Must be 'guitar' or 'programming'.");
        }

        // SQL command to increment the selected timeType by 1
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

    public static Connection databaseConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:csc205.db");
    }
}
