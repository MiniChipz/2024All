import malte.loginGui.GUI.loginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApp {
    private JFrame currentFrame;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            MainApp app = new MainApp();
            app.showLoginScreen();
        });
    }

    public void showLoginScreen() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new loginGUI(this);
        currentFrame.setVisible(true);
    }

    public void showUserManagement(boolean isAdmin) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }

        JFrame frame = new JFrame("User Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Color backgroundColor = new Color(33, 33, 33);
        frame.getContentPane().setBackground(backgroundColor);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        String[] options = {"Create User", "Delete User"};
        JComboBox<String> menuDropdown = new JComboBox<>(options);
        styleComboBox(menuDropdown);

        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dropdownPanel.setBackground(backgroundColor);
        dropdownPanel.add(menuDropdown);
        mainPanel.add(dropdownPanel, BorderLayout.NORTH);

        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(backgroundColor);

        contentPanel.add(createUserPanel, "Create User");
        contentPanel.add(deleteUserPanel, "Delete User");
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        menuDropdown.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                cardLayout.show(contentPanel, (String) menuDropdown.getSelectedItem());
            }
        });

        frame.add(mainPanel);
        currentFrame = frame;
        currentFrame.setVisible(true);
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(new Color(55, 55, 55));
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setPreferredSize(new Dimension(200, 30));

        // Style the ComboBox popup menu
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(isSelected ? new Color(70, 70, 70) : new Color(55, 55, 55));
                setForeground(Color.WHITE);
                return this;
            }
        });

        // Style the arrow button
        for (Component comp : comboBox.getComponents()) {
            if (comp instanceof JButton) {
                ((JButton) comp).setBackground(new Color(55, 55, 55));
            }
        }
    }

    public void login(String username, String password) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:csc205.db");
            String[] user = DataBase.checkLogin(conn, "Users", username.toLowerCase(), password);

            if (!user[0].equals("null")) {
                JOptionPane.showMessageDialog(null, "Login Successful! \nWelcome, " + user[2]);
                SwingUtilities.invokeLater(() -> showUserManagement(Boolean.parseBoolean(user[3])));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean registerUser(String username, String password, String fullName, boolean isAdmin) throws SQLException {
        return username != null && !username.trim().isEmpty() &&
                password != null && !password.trim().isEmpty() &&
                fullName != null && !fullName.trim().isEmpty() &&
                !DataBase.doesUserExists(DataBase.databaseConnect(), username);
    }
}