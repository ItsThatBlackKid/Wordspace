import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by sheku on 25/11/2015.
 */
public class Status extends JPanel {
    public JMenu[] menus = new JMenu[5];
    public JLabel lines = new JLabel();
    public JLabel words = new JLabel();
    public JLabel position = new JLabel();

    public JButton settings = new JButton("Settings");

    public Status() {
        setLayout(new FlowLayout(0, 4, 0));
        setPreferredSize(new Dimension(this.getWidth(), 16));
        setBackground(Color.lightGray);
        setForeground(Color.white);

        add(lines);
        add(words);
        add(position);

    }

    private void addListener() {
        lines.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }
}