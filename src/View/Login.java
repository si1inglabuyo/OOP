package View;
import Controller.ReadUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Model.Database;
import Model.User;


public class Login{

    public Login(Database database) {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(115,0,182,0));

        JLabel title = new JLabel("Login", 40, GUIConstants.blue, Font.BOLD);
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(null);
        center.setBorder(BorderFactory.createEmptyBorder(23, 315, 17, 315));
        center.setBackground(null);

        JTextField email = new JTextField("Email");
        center.add(email);
        center.add(Box.createVerticalStrut(10));

        JTextField password = new JTextField("Password");
        center.add(password);
        center.add(Box.createVerticalStrut(10));

        JButton login = new JButton("Login", 30, 20);
        login.setPreferredSize(new Dimension(200, 45));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(login);
        center.add(buttonPanel);

        login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(email.isEmpty() || password.isEmpty()) {
                    new Alert("Please fill in all fields", frame, "alert.png");
                    return;
                }
                ReadUser read = new ReadUser(email.getText(), password.getText(), database);
                if(read.loggedIn()) {
                    User user = read.getUser();
                    new Alert("Logged in successfully", frame, "success.png");

                } else {
                    new Alert("Invalid email or password", frame, "alert.png");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });



        panel.add(center, BorderLayout.CENTER);

        JLabel createAcc = new JLabel("Don't have an account? Create one", 20, GUIConstants.black, Font.BOLD);
        createAcc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAcc.setHorizontalAlignment(JLabel.CENTER);
        panel.add(createAcc, BorderLayout.SOUTH);

        createAcc.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                new Welcome(database);
                frame.dispose();

            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                createAcc.setForeground(GUIConstants.grey);

            }
            @Override
            public void mouseExited(MouseEvent e) {
                createAcc.setForeground(GUIConstants.black);
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.requestFocus();

    }
}
