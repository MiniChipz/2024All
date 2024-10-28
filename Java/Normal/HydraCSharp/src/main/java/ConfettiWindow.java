import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ConfettiWindow extends JFrame {
    private final ConfettiPanel confettiPanel;
    private final ConfettiController controller;
    private final JLabel randomStringLabel;
    private final Connection conn; // Single shared connection
    private final JLabel leftLabel;
    private final JLabel rightLabel;

    public ConfettiWindow() throws SQLException {
        conn = Database.databaseConnect(); // Open connection here
        controller = new ConfettiController();
        confettiPanel = new ConfettiPanel();

        setTitle("Hydras wheel of fortune");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Title label at the top, centered within a container
        JLabel titleLabel = new JLabel("Programming or Guitar", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center alignment
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel);

        // Random string label in the center
        randomStringLabel = new JLabel("", SwingConstants.CENTER);
        randomStringLabel.setFont(new Font("Arial", Font.BOLD, 24));
        randomStringLabel.setForeground(new Color(50, 50, 50));
        confettiPanel.add(randomStringLabel);
        randomStringLabel.setVisible(false);

        // Panel for the two labels under the title
        JPanel labelsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        labelsPanel.setOpaque(false);

        // Initialize values and set text for labels
        leftLabel = new JLabel("", SwingConstants.CENTER);
        rightLabel = new JLabel("", SwingConstants.CENTER);
        updateLabels(); // Initialize with current database values

        leftLabel.setFont(new Font("Arial", Font.BOLD, 16));
        rightLabel.setFont(new Font("Arial", Font.BOLD, 16));

        labelsPanel.add(leftLabel);
        labelsPanel.add(rightLabel);

        // Header panel to contain both title and labels
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        headerPanel.add(titlePanel); // Add centered title
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing
        headerPanel.add(labelsPanel);

        // Styled button
        JButton randomButton = new JButton("Click Me!");
        randomButton.setPreferredSize(new Dimension(100, 40));
        randomButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        randomButton.setBackground(new Color(240, 240, 240));
        randomButton.setFocusPainted(false);

        // Button action listener to update the database and labels
        randomButton.addActionListener(e -> {
            playSoundEffect();
            String randomString = controller.handleButtonClick();
            randomStringLabel.setText(randomString);
            randomStringLabel.setVisible(true);
            confettiPanel.startConfetti();
            try {
                // Increment the appropriate counter based on the random string
                if (randomString.equals("DO GUITAR RIFF")) {
                    Database.incrementTime(conn, "guitar");
                } else {
                    Database.incrementTime(conn, "programming");
                }
                updateLabels(); // Update labels after incrementing
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        setLayout(new BorderLayout(0, 10));
        add(headerPanel, BorderLayout.NORTH);
        add(confettiPanel, BorderLayout.CENTER);
        add(randomButton, BorderLayout.SOUTH);

        // Add padding around the components
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a window listener to close the database connection when the window is closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Method to update the left and right labels based on current database values
    private void updateLabels() throws SQLException {
        int[] val = Database.getValues(conn);
        rightLabel.setText("Guitar: " + val[0]);
        leftLabel.setText("Programming: " + val[1]);
    }

    private void playSoundEffect() {
        try {
            // Use getResourceAsStream for consistent file access
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/HappyHydraSound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
