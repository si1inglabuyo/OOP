package View;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {

    public LoadingScreen() {
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new BorderLayout());


        ImageIcon loadingIcon = new ImageIcon(LoadingScreen.class.getResource("/imgs/load.gif"));

        // Create JLabel with the animated icon
        JLabel label = new JLabel(loadingIcon, SwingConstants.CENTER);

        panel.add(label, BorderLayout.CENTER);
        add(panel);
        setVisible(true);

    }

    public void close() {
        dispose();
    }



}
