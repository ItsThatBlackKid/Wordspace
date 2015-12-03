import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




public class Wordspace extends JFrame {
    //Variable
    public JMenuBar menuBar = new JMenuBar();
    public JTextArea magic = new JTextArea();
    public JLabel currentLine = new JLabel();
    public JFileChooser chooser = new JFileChooser();
    public Status status = new Status();
    public Tools tools = new Tools();
    public JButton settings = new JButton("Settings");
    public JMenu[] menus = new JMenu[5];

    public static void main(String[] args) {
        new Wordspace().setVisible(true);
    }

    //Declaring the interface
    public Wordspace() {
        setTitle(System.getProperty("user.name"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 700);
        setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        menu();

        writingBoard();

        setJMenuBar(menuBar);

        add(magic);
        add(status, BorderLayout.SOUTH);
        add(tools, BorderLayout.NORTH);
    }

    public void updateCount(JLabel label) {
        label.setText("Lines: " + magic.getLineCount());
    }

    public void updateWords(JLabel label) {
        String docWord = magic.getText();
        String[] wordArray = docWord.split("\\s+");
        int wordCount = wordArray.length;

        label.setText("Words: " + wordCount);
    }

    void writingBoard() {
        magic.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {

               status.position.setText("Position: " + e.getDot());
            }
        });

        magic.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCount(status.lines);
                updateWords(status.words);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCount(status.lines);
                updateWords(status.words);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCount(status.lines);
                updateWords(status.words);
            }
        });
    }

    void menu() {
        String menuStrings[] = {
                "File",
                "Find",
                "View",
                "Format",
                "Analyze",
        };



        //Adding all Menus to the menu bar automatically
        for (int i = 0; i < 5; i++) {
            menus[i] = new JMenu(menuStrings[i]);

            menuBar.add(menus[i]);
        }

        addItems();

    }

    JMenuItem fileItems[] = new JMenuItem[3];
    JMenuItem viewItems[] = new JMenuItem[4];


    //Method is for adding items to Menus
    void addItems() {
        String fileStrings[] = {
                "Open...",
                "Save As...",
                "Save",

        };

        for (int w = 0; w < 2; w++) {
            viewItems[w] = new JMenuItem("Turn off Status Strip");
            menus[2].add(viewItems[w]);
        }



        //Adding all Menu Items to "File" automatically
        for (int w = 0; w < 3; w++) {
            fileItems[w] = new JMenuItem(fileStrings[w]);

            //Below "Open" a separator is added
            if(w == 1)
                menus[0].addSeparator();

            menus[0].add(fileItems[w]);
        }



        addListeners();
    }


    //Item Listeners are added here
    void addListeners() {
        fileItems[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WebsterFileExplorer().showSaveExplorer(Wordspace.this);
            }
        });

        viewItems[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Wordspace.this.remove(status);
            }
        });

    }



    void save() {

    }

    void saveAs() {
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(selectedFile);
            } catch (IOException e) {
                System.out.println("IOException error");
                JOptionPane.showInternalMessageDialog(this, "Sorry, didn't quite catch that",
                        "Houston, there's a problem",
                        JOptionPane.ERROR_MESSAGE);
            }

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    void open() {
        int result = chooser.showOpenDialog(this);

    }

    public class Tools extends JPanel {
        public JButton settings = new JButton(new ImageIcon("src/images/gear-ico.png"));

        void manageButton() {
            settings.setPreferredSize(new Dimension(30, 30));

            settings.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Settings();
                }
            });
        }
        public Tools() {
            setLayout(new FlowLayout(4, 2, 0));
            setPreferredSize(new Dimension(this.getWidth(), 30));
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.WHITE);

            manageButton();

            add(settings);
        }
    }
}