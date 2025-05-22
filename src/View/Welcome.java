package View;

import Controller.CreateUser;
import Model.Database;
import Model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Welcome {

    public Welcome(Database database) {
        JFrame frame = new JFrame();


        JLabel title = new JLabel("Welcome to Twitter ng Pinas", 40, GUIConstants.blue, Font.BOLD);
        title.setHorizontalAlignment(JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(53,84,76,84));
        panel.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(null);
        center.setBorder(BorderFactory.createEmptyBorder(23, 231, 17, 231));


        JTextField firstName = new JTextField("First Name");
        center.add(firstName);
        center.add(Box.createVerticalStrut(10));

        JTextField lastName = new JTextField("Last Name");
        center.add(lastName);
        center.add(Box.createVerticalStrut(10));

        JTextField email = new JTextField("Email");
        center.add(email);
        center.add(Box.createVerticalStrut(10));

        JTextField password = new JTextField("Password");
        center.add(password);
        center.add(Box.createVerticalStrut(10));

        JTextField confirmPassword = new JTextField("Confirm Password");
        center.add(confirmPassword);
        center.add(Box.createVerticalStrut(10));



        JButton createAcc = new JButton("Create Account", 30, 20);
        createAcc.setPreferredSize(new Dimension(200, 45));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(createAcc);
        center.add(buttonPanel);


        createAcc.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (firstName.isEmpty()) {
                    new Alert("First Name is required", frame, "alert.png");
                    return;
                }
                if (lastName.isEmpty()) {
                    new Alert("Last Name is required", frame, "alert.png");
                    return;
                }
                if (email.isEmpty()) {
                    new Alert("Email is required", frame, "alert.png");
                    return;
                }
                if (password.isEmpty()) {
                    new Alert("Password cannot be empty", frame, "alert.png");
                    return;
                }

                if (password.getText().length() < 8) {
                    new Alert("Password must be at least 8 characters", frame, "alert.png");
                    return;
                }

                if (confirmPassword.isEmpty()) {
                    new Alert("Please confirm your password", frame, "alert.png");
                    return;
                }
                if (!password.getText().equals(confirmPassword.getText())) {
                    new Alert("Password doesn't match!", frame, "alert.png");
                    return;

                }
                User u = new User();
                u.setFirstName(firstName.getText());
                u.setLastName(lastName.getText());
                u.setEmail(email.getText());
                u.setPassword(password.getText());

                CreateUser create = new CreateUser(u, database);
                if(!create.isEmailUsed()){
                    create.create();
                    u = create.getUser();
                    new Alert("Account Created Successfully", frame, "success.png");
                } else {
                    new Alert("Email already used", frame, "alert.png");
                }

            }


            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        panel.add(center, BorderLayout.CENTER);

        JLabel login = new JLabel("Already have an account? Login",
                20, GUIConstants.black, Font.BOLD);

        login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login(database);
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                login.setForeground(GUIConstants.grey);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setForeground(GUIConstants.black);
            }
        });

        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setHorizontalAlignment(JLabel.CENTER);
        panel.add(login, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.requestFocus();







    }
}
