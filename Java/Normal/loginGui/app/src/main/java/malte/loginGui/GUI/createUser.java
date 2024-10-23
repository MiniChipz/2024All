package malte.loginGui.GUI;

import malte.loginGui.MainApp;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.border.EmptyBorder;

public class createUser extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JTextField fullNameField;
    private final JComboBox<String> adminComboBox;
    private final MainApp mainApp;

    public createUser() {
        mainApp = new MainApp();
        setTitle("Create User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set dark theme colors
        Color backgroundColor = new Color(33, 33, 33);
        Color foregroundColor = new Color(200, 200, 200);
        Color fieldBackground = new Color(55, 55, 55);
        Color buttonColor = new Color(41, 128, 185);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Components
        usernameField = createStyledTextField();
        passwordField = createStyledPasswordField();
        fullNameField = createStyledTextField();
        String[] adminOptions = {"No", "Yes"};
        adminComboBox = new JComboBox<>(adminOptions);
        styleComboBox(adminComboBox);

        // Add components with labels
        mainPanel.add(createStyledLabel("Username"));
        mainPanel.add(usernameField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(createStyledLabel("Password"));
        mainPanel.add(passwordField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(createStyledLabel("Full Name"));
        mainPanel.add(fullNameField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(createStyledLabel("Admin"));
        mainPanel.add(adminComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Submit button
        JButton submitButton = new JButton("Create User");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(submitButton);
        submitButton.addActionListener(e -> {
            try {
                handleSubmit();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        mainPanel.add(submitButton);

        add(mainPanel);
        setVisible(true);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(200, 200, 200));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBackground(new Color(55, 55, 55));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                new loginGUI.RoundedBorder(10, new Color(70, 70, 70)),
                BorderFactory.createEmptyBorder(-2, 1, 0, 10)  // Adjusted inner padding
        ));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setBackground(new Color(55, 55, 55));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                new loginGUI.RoundedBorder(10, new Color(70, 70, 70)),
                BorderFactory.createEmptyBorder(-2, 1, 0, 10)  // Adjusted inner padding
        ));
        return field;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        comboBox.setBackground(new Color(70, 70, 70));
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ((JComponent) comboBox.getRenderer()).setBackground(new Color(70, 70, 70));
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

    private void handleSubmit() throws SQLException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String fullName = fullNameField.getText();
        boolean isAdmin = adminComboBox.getSelectedIndex() == 1;

        if (mainApp.registerUser(username, password, fullName, isAdmin)) {
            JOptionPane.showMessageDialog(this, "User created successfully!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "User creation failed. Please try again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        fullNameField.setText("");
        adminComboBox.setSelectedIndex(0);
    }
}