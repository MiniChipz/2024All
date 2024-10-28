
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;

public class loginGUI extends JFrame {
    JTextField jTextField1;
    JPasswordField jPasswordField1;
    JButton jButton1;
    private JLabel jLabel1, jLabel2, jLabel3;
    private MainApp mainApp;

    public loginGUI(MainApp mainApp) {
        this.mainApp = mainApp;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(new Color(33, 33, 33));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7);

        jLabel1 = new JLabel("Welcome");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(jLabel1, gbc);

        jLabel2 = new JLabel("Username");
        jLabel2.setForeground(new Color(200, 200, 200));
        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(7, 7, 2, 7);
        mainPanel.add(jLabel2, gbc);

        jTextField1 = new JTextField(20);
        styleTextField(jTextField1);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 7, 7, 7);
        mainPanel.add(jTextField1, gbc);

        jLabel3 = new JLabel("Password");
        jLabel3.setForeground(new Color(200, 200, 200));
        jLabel3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(7, 7, 2, 7);
        mainPanel.add(jLabel3, gbc);

        jPasswordField1 = new JPasswordField(20);
        styleTextField(jPasswordField1);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 7, 7, 7);
        mainPanel.add(jPasswordField1, gbc);

        jButton1 = new JButton("Login");
        styleButton(jButton1);
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 7, 7, 7);
        mainPanel.add(jButton1, gbc);

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    performLogin();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jPasswordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        performLogin();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void performLogin() throws SQLException {
        String username = jTextField1.getText();
        String password = new String(jPasswordField1.getPassword());
        mainApp.login(username, password);
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(new Color(55, 55, 55));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(10, new Color(70, 70, 70)),
                BorderFactory.createEmptyBorder(-2, 1, 0, 10)
        ));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 40));
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(new RoundedBorder(10, new Color(41, 128, 185)));
        button.setPreferredSize(new Dimension(200, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
    }

    public static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }
}