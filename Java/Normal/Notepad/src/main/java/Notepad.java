import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {

    private String currentFileName = "Unnamed file";
    private JTextArea textArea;
    private File currentFile;
    private JFileChooser fileChooser;
    private JMenuItem globalSaveAsButton;
    private JMenuItem globalSaveButton;
    private JMenuItem globalOpenButton;
    private JMenuItem globalZoomInButton;
    private JMenuItem globalZoomOutButton;
    private JMenuItem globalZoomResetButton;

    public static void main(String[] args) {
        new Notepad().setVisible(true);
    }

    public Notepad() {
        SwingUtilities.invokeLater(() -> {
            setTitle(currentFileName);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1000, 600);
            setLocationRelativeTo(null);

            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files only", "txt"));

            addGui();
        });
    }
    private void addGui() {
        toolBar();
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);
        addShortcut(textArea);
    }

    private void toolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JMenuBar menuBar = new JMenuBar();
        toolBar.add(menuBar);
        menuBar.add(addFileMenu());
        menuBar.add(addFormatMenu());
        menuBar.add(addViewMenu());

        add(toolBar, BorderLayout.NORTH);
    }

    private JMenu addFileMenu() {
        JMenu fileMenu = new JMenu();
        fileMenu.setPreferredSize(new Dimension(30, 20));
        fileMenu.setMinimumSize(new Dimension(30, 20));
        fileMenu.setText("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFileName = "Unnamed file";
                setTitle(currentFileName);
                textArea.setText("");

                currentFile = null;
            }
        });
        fileMenu.add(newMenuItem);
        JMenuItem openMenuItem = getjMenuItem(newMenuItem);
        fileMenu.add(openMenuItem);
        globalOpenButton = openMenuItem;

        JMenuItem saveAsMenuItem = getjMenuItem();
        fileMenu.add(saveAsMenuItem);
        globalSaveAsButton = saveAsMenuItem;

        JMenuItem saveMenuItem = getMenuItem(saveAsMenuItem);
        fileMenu.add(saveMenuItem);
        globalSaveButton = saveMenuItem;

        return fileMenu;
    }

    private JMenuItem getMenuItem(JMenuItem saveAsMenuItem) {
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFile == null) {
                    saveAsMenuItem.doClick();
                }

                if (currentFile == null) return;

                try {
                    FileWriter fileWriter = new FileWriter(currentFile);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(textArea.getText());
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        });
        return saveMenuItem;
    }

    private JMenuItem getjMenuItem() {
        JMenuItem saveAsMenuItem = new JMenuItem("save as");
        saveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showSaveDialog(Notepad.this);

                if (result != JFileChooser.APPROVE_OPTION) return;
                try {
                    File selectedFile = fileChooser.getSelectedFile();

                    String fileName = selectedFile.getName();
                    if (!fileName.substring(fileName.length() -4).equalsIgnoreCase(".txt")) {
                        selectedFile = new File(selectedFile.getAbsoluteFile() + ".txt");
                    }
                    selectedFile.createNewFile();

                    FileWriter fileWriter = new FileWriter(selectedFile);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(textArea.getText());
                    fileWriter.close();

                    currentFileName = fileName;
                    setTitle(currentFileName);
                    currentFile = selectedFile;

                    JOptionPane.showMessageDialog(Notepad.this, "Saved file " + fileName);
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        });
        return saveAsMenuItem;
    }

    private JMenuItem getjMenuItem(JMenuItem newMenuItem) {
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(Notepad.this);

                if (result != JFileChooser.APPROVE_OPTION) return;
                try {
                    newMenuItem.doClick();

                    File selectedFile = fileChooser.getSelectedFile();
                    currentFile = selectedFile;

                    currentFileName = selectedFile.getName();
                    setTitle(currentFileName);

                    FileReader fileReader = new FileReader(selectedFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    StringBuilder fileText = new StringBuilder();
                    String readText;
                    while ((readText = bufferedReader.readLine()) != null) {
                        fileText.append(readText).append("\n");
                    }

                    textArea.setText(fileText.toString());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        return openMenuItem;
    }

    private JMenu addFormatMenu() {
        JMenu formatMenu = getjMenu();

        JMenu alignTextMenu = new JMenu("Align text");

        JMenuItem alignTextLeftMenuItem = new JMenuItem("Left");
        alignTextMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            }
        });
        alignTextMenu.add(alignTextLeftMenuItem);

        JMenuItem alignTextRightMenuItem = new JMenuItem("Right");
        alignTextRightMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            }
        });
        alignTextMenu.add(alignTextRightMenuItem);
        return formatMenu;
    }

    private JMenu getjMenu() {
        JMenu formatMenu = new JMenu("Format");

        JCheckBoxMenuItem wordWrapMenuItem = new JCheckBoxMenuItem("Word wrap");
        wordWrapMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isChecked = wordWrapMenuItem.getState();
                if (isChecked) {
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                } else {
                    textArea.setLineWrap(false);
                    textArea.setWrapStyleWord(false);
                }
            }
        });
        formatMenu.add(wordWrapMenuItem);
        return formatMenu;
    }

    private JMenu addViewMenu() {
        JMenu viewMenu = new JMenu("View");

        JMenu zoomMenu = new JMenu("Zoom");

        JMenuItem zoomInMenuItem = new JMenuItem("Zoom in");
        zoomInMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font currentFont = textArea.getFont();
                textArea.setFont(new Font(
                        currentFont.getName(),
                        currentFont.getStyle(),
                        currentFont.getSize() + 1
                ));
            }
        });
        zoomMenu.add(zoomInMenuItem);
        globalZoomInButton = zoomInMenuItem;

        JMenuItem zoomOutMenuItem = new JMenuItem("Zoom out");

        zoomOutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font currentFont = textArea.getFont();

                textArea.setFont(new Font(
                        currentFont.getName(),
                        currentFont.getStyle(),
                        currentFont.getSize() - 1
                ));
            }
        });
        zoomMenu.add(zoomOutMenuItem);
        globalZoomOutButton = zoomOutMenuItem;

        JMenuItem zoomRestoreMenuItem = new JMenuItem("Reset zoom");
        zoomRestoreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font currentFont = textArea.getFont();
                textArea.setFont(new Font(
                        currentFont.getName(),
                        currentFont.getStyle(),
                        12
                ));
            }
        });
        zoomMenu.add(zoomRestoreMenuItem);
        globalZoomResetButton = zoomRestoreMenuItem;

        viewMenu.add(zoomMenu);

        return viewMenu;
    }

    private void addShortcut(JTextArea textArea) {
    KeyStroke saveKeyStroke = KeyStroke.getKeyStroke("control S");
    KeyStroke openKeyStroke = KeyStroke.getKeyStroke("control O");
    KeyStroke zoomOutKeyStroke = KeyStroke.getKeyStroke("control MINUS");
    KeyStroke zoomInKeyStroke = KeyStroke.getKeyStroke("control PLUS");
    KeyStroke zoomResetKeyStroke = KeyStroke.getKeyStroke("control R");

    InputMap inputMap = textArea.getInputMap(JComponent.WHEN_FOCUSED);
    ActionMap actionMap = textArea.getActionMap();

    String saveActionKey = "SaveShortcut";
    String openActionKey = "OpenShortcut";
    String zoomOutActionKey = "ZoomOutShortcut";
    String zoomInActionKey = "ZoomInShortcut";
    String zoomResetActionKey = "ZoomResetShortcut";

    inputMap.put(saveKeyStroke, saveActionKey);
    inputMap.put(openKeyStroke, openActionKey);
    inputMap.put(zoomOutKeyStroke, zoomOutActionKey);
    inputMap.put(zoomInKeyStroke, zoomInActionKey);
    inputMap.put(zoomResetKeyStroke, zoomResetActionKey);

    actionMap.put(zoomResetActionKey, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            globalZoomResetButton.doClick();
        }
    });

    actionMap.put(zoomInActionKey, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            globalZoomInButton.doClick();
        }
    });

    actionMap.put(zoomOutActionKey, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            globalZoomOutButton.doClick();
        }
    });

    actionMap.put(openActionKey, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            globalOpenButton.doClick();
        }
    });

    actionMap.put(saveActionKey, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentFileName.equals("Unnamed file")) {
                globalSaveAsButton.doClick();
            } else {
                globalSaveButton.doClick();
            }
        }
    });
}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
