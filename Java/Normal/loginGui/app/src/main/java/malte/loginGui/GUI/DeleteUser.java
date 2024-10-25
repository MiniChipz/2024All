package malte.loginGui.GUI;

import malte.loginGui.DataBase;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DeleteUser extends JFrame {
    private final JComboBox<String> userComboBox;
    private final JPanel mainPanel;

    public DeleteUser(boolean isUserAdmin) {
        setTitle("Delete User");

        // Set dark theme colors
        Color backgroundColor = new Color(33, 33, 33);
        Color foregroundColor = new Color(200, 200, 200);
        Color fieldBackground = new Color(55, 55, 55);
        Color buttonColor = new Color(41, 128, 185);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create and populate user combo box
        Vector<String> userList = new Vector<>();
        try {
            Connection conn = DataBase.databaseConnect();
            ResultSet rs = DataBase.getAllUsers(conn);
            while (rs.next()) {
                userList.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userComboBox = new JComboBox<>(userList);
        styleComboBox(userComboBox);

        // Add components
        JLabel label = createStyledLabel();
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(label);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        userComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboBoxPanel.setBackground(backgroundColor);
        comboBoxPanel.add(userComboBox);
        mainPanel.add(comboBoxPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Delete button
        JButton deleteButton = new JButton("Delete User");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(deleteButton);
        deleteButton.addActionListener(e -> handleDelete());
        mainPanel.add(deleteButton);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JLabel createStyledLabel() {
        JLabel label = new JLabel("Select User to Delete");
        label.setForeground(new Color(200, 200, 200));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setMaximumSize(new Dimension(300, 40));
        comboBox.setPreferredSize(new Dimension(250, 40));
        comboBox.setBackground(new Color(55, 55, 55));
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

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

    private void styleButton(JButton button) {
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(new loginGUI.RoundedBorder(10, new Color(41, 128, 185)));
        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void handleDelete() {
        String selectedUser = (String) userComboBox.getSelectedItem();
        if (selectedUser != null) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete user: " + selectedUser + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = DataBase.databaseConnect();
                    DataBase.deleteUser(conn, selectedUser);
                    JOptionPane.showMessageDialog(this, "User deleted successfully!");
                    refreshUserList();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this,
                            "Error deleting user: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void refreshUserList() {
        userComboBox.removeAllItems();
        try {
            Connection conn = DataBase.databaseConnect();
            ResultSet rs = DataBase.getAllUsers(conn);
            while (rs.next()) {
                userComboBox.addItem(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}