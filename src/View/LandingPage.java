package View;

import Model.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LandingPage {

    public LandingPage(Database database) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/imgs/front_page.png"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        JButton imageButton = new JButton("Join Us!", 50,25);
        imageButton.setBounds(100, 435, 150, 50);

        imageButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                new Welcome(database);
                frame.dispose();

            }
        });

        backgroundPanel.add(imageButton);

        frame.setVisible(true);
    }
}