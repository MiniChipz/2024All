import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfettiWindow extends JFrame {
    private final ConfettiPanel confettiPanel;
    private final ConfettiController controller;
    private final JLabel randomStringLabel;
    private final Connection conn; // Single shared connection
    private final JLabel leftLabel;
    private final JLabel rightLabel;

    public ConfettiWindow() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + new File("csc205.db").getAbsolutePath());
        controller = new ConfettiController();
        confettiPanel = new ConfettiPanel();

        setTitle("Hydras wheel of fortune");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Programming or Guitar", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center alignment
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel);

        randomStringLabel = new JLabel("", SwingConstants.CENTER);
        randomStringLabel.setFont(new Font("Arial", Font.BOLD, 24));
        randomStringLabel.setForeground(new Color(50, 50, 50));
        confettiPanel.add(randomStringLabel);
        randomStringLabel.setVisible(false);

        JPanel labelsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        labelsPanel.setOpaque(false);

        leftLabel = new JLabel("", SwingConstants.CENTER);
        rightLabel = new JLabel("", SwingConstants.CENTER);
        updateLabels();

        leftLabel.setFont(new Font("Arial", Font.BOLD, 16));
        rightLabel.setFont(new Font("Arial", Font.BOLD, 16));

        labelsPanel.add(leftLabel);
        labelsPanel.add(rightLabel);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        headerPanel.add(titlePanel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing
        headerPanel.add(labelsPanel);

        JButton randomButton = getjButton();
        setLayout(new BorderLayout(0, 10));
        add(headerPanel, BorderLayout.NORTH);
        add(confettiPanel, BorderLayout.CENTER);
        add(randomButton, BorderLayout.SOUTH);

        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());;
                }
            }
        });
    }

    private JButton getjButton() {
        JButton randomButton = new JButton("Click Me!");
        randomButton.setPreferredSize(new Dimension(100, 40));
        randomButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        randomButton.setBackground(new Color(240, 240, 240));
        randomButton.setFocusPainted(false);

        randomButton.addActionListener(e -> {
            String randomString = controller.handleButtonClick();
            randomStringLabel.setText(randomString);
            randomStringLabel.setVisible(true);
            confettiPanel.startConfetti();
            try {
                if (randomString.equals("DO GUITAR")) {
                    Database.incrementTime(conn, "guitar");
                } else {
                    Database.incrementTime(conn, "programming");
                }
                updateLabels();
            } catch (SQLException ex) {
                System.out.println("Error incrementing time: " + ex.getMessage());
            }
        });
        return randomButton;
    }

    private void updateLabels() throws SQLException {
        int[] val = Database.getValues(conn);
        rightLabel.setText("Guitar: " + val[0]);
        leftLabel.setText("Programming: " + val[1]);
    }
}
