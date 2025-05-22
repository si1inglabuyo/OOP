package View;

import javax.swing.*;
import java.awt.*;

public class Alert {


    /**
     * This class is used to create an alert dialog with a message and an icon.
     * @param content The message to be displayed in the alert dialog.
     * @param parent The parent frame of the alert dialog.
     * @param iconPath The path to the icon image file.
     */

    public Alert(String content, javax.swing.JFrame parent, String iconPath) {

        JFrame frame = new JFrame();
        frame.setSize(470, 170);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panel.setBackground(GUIConstants.white);


        ImageIcon icon = new ImageIcon(Alert.class.getResource(iconPath));
        icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel title = new JLabel(icon);
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title, BorderLayout.NORTH);
         

        JLabel msg = new JLabel(content,20, GUIConstants.black, Font.BOLD);
        msg.setHorizontalAlignment(JLabel.CENTER);
        panel.add(msg, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);


    }


}
