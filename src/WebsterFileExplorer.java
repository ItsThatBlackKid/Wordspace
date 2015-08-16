import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sheku on 14/08/2015.
 */
public class WebsterFileExplorer extends JDialog implements ActionListener {
    JPanel dialog = new JPanel();
    JPanel fileView = new JPanel();

    static final int APPROVE_OPTION = 0;

    public void showSaveExplorer(Component parent) {
        setTitle("Save");
        setVisible(true);
        setSize(600, 500);
        setLayout(new BorderLayout());

        saveLayout();
    }

    void saveLayout() {
        dialog.setLayout(new FlowLayout(4, 2, 2));
        dialog.setPreferredSize(new Dimension(this.getWidth(), 30));
        dialog.setBackground(Color.LIGHT_GRAY);

        JButton saveBTN = new JButton("Save");
        saveBTN.setSize(40, 50);
        saveBTN.setActionCommand("save");
        saveBTN.addActionListener(this);

        dialog.add(new JButton("Cancel"));
        dialog.add(saveBTN, BorderLayout.SOUTH);

        add(dialog, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getSource().toString();

        if(c.equalsIgnoreCase("save")) {

        }
    }
}
