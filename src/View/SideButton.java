package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Database;
import Model.User;

@SuppressWarnings("serial")
public class SideButton extends JPanel {

    public SideButton(String text, String pic, User user, Database database, JFrame f) {
        super(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setMaximumSize(new Dimension(182, 50));
        setBackground(GUIConstants.white);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon originalIcon = new ImageIcon("src/imgs/" + pic + ".png");

        Image resizedImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel img = new JLabel(resizedIcon);
        add(img);

        add(new View.JLabel(text, 17, GUIConstants.textAreaHint, Font.BOLD));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(GUIConstants.white);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(GUIConstants.hover);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                new CustomView(text, user, database);
                f.dispose();
            }
        });
    }

}
