package GUI;

import mainPackage.Main;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class MainWindow extends JFrame {
    private JTextArea output;
    private int lockCaretPosition;

    public MainWindow() {
        SwingUtilities.invokeLater(() -> {
            setTitle("Terminal");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            setSize(1000, 600);
            setLocationRelativeTo(null);

            // Initialize JTextArea
            output = new JTextArea();
            output.setEditable(true);
            output.setFont(new Font("Monospaced", Font.PLAIN, 14));
            output.setLineWrap(true);
            output.setWrapStyleWord(true);

            // Set initial prompt
            lockCaretPosition = Main.currentDir.toString().length() + 1;
            output.setText(Main.currentDir.toString() + ">");
            output.setCaretPosition(lockCaretPosition);

            JScrollPane scrollPane = new JScrollPane(output);

            // Add key listener to handle commands
            output.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        e.consume();
                        String command = handleCommand();
                        try {
                            String commandOutput = Main.commandHandler(command);
                            if (!(Objects.equals(commandOutput, ""))) {
                            output.append("\n" + commandOutput);
                            }
                            output.append("\n" + Main.currentDir.toString() + ">");
                            lockCaretPosition = output.getText().length();
                            output.setCaretPosition(lockCaretPosition);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            });

            // Apply document filter with reference to the JTextArea
            AbstractDocument doc = (AbstractDocument) output.getDocument();
            doc.setDocumentFilter(new TerminalDocumentFilter(output));

            // Prevent caret from moving back to locked region
            output.addCaretListener(e -> {
                if (e.getDot() < lockCaretPosition) {
                    SwingUtilities.invokeLater(() -> output.setCaretPosition(lockCaretPosition));
                }
            });

            add(scrollPane, BorderLayout.CENTER);
            setVisible(true);
        });
    }

    private String handleCommand() {
        // Get the user's input
        String fullText = output.getText();
        String[] lines = fullText.split("\n");
        String lastLine = lines[lines.length - 1];
        String command = lastLine.replace(Main.currentDir.toString() + ">", "").strip();

        System.out.println("Command executed: " + command);

        // Append a new prompt and update the locked caret position
//        output.append("\n" + Main.currentDir.toString() + ">");
        lockCaretPosition = output.getText().length();
        output.setCaretPosition(lockCaretPosition);
        return command;
    }

    static class TerminalDocumentFilter extends DocumentFilter {
        private final JTextArea textArea;

        public TerminalDocumentFilter(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            // Allow edits only after the locked region
            MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(textArea);
            if (offset >= mainWindow.lockCaretPosition) {
                fb.insertString(offset, string, attr);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            // Allow deletion only after the locked region
            MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(textArea);
            if (offset >= mainWindow.lockCaretPosition) {
                fb.remove(offset, length);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            // Allow replacement only after the locked region
            MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(textArea);
            if (offset >= mainWindow.lockCaretPosition) {
                fb.replace(offset, length, text, attrs);
            }
        }
    }
}
