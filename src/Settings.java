import javax.swing.*;
import java.awt.*;

/**
 * Created by sheku on 16/08/2015.
 */
public class Settings extends JDialog {
    public  Settings() {
        setVisible(true);
        setSize(600, 700);
        setLayout(new FlowLayout());

        add(new JLabel("Hello, World"));
    }
}
