package malte.loginGui;

import malte.loginGui.GUI.createUser;
import malte.loginGui.GUI.loginGUI;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApp {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginGUI(new MainApp()).setVisible(true);
            }
        });
    }

    public void login(String username, String password) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:csc205.db");

            String[] user = DataBase.checkLogin(conn, "Users", username.toLowerCase(), password);

            if (!user[0].equals("null")) {
                JOptionPane.showMessageDialog(null, "Login Successful! \nWelcome, " + user[2] + "\nAdmin: " + user[3]);
                SwingUtilities.invokeLater(() -> new createUser(Boolean.parseBoolean(user[3])));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
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
    public boolean registerUser(String username, String password, String fullName, boolean isAdmin, boolean isUserAdmin) throws SQLException {
        return username != null && !username.trim().isEmpty() &&
                password != null && !password.trim().isEmpty() &&
                fullName != null && !fullName.trim().isEmpty() &&
                !DataBase.doesUserExists(DataBase.databaseConnect(), username);

        // Here you would typically:
        // 1. Hash the password
        // 2. Check if username already exists
        // 3. Store in database
        // 4. Handle any errors

        // For demonstration, we'll just return true
        // Replace this with actual implementation
    }
}
