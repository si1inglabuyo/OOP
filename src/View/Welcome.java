package View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Controller.CreateUser;
import Model.Database;
import Model.User;

public class Welcome {

    public Welcome(Database database) {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(53, 84, 76, 84));

        JLabel welcomeTitle = new JLabel("Signup to IskOnnect", 40, GUIConstants.background, Font.BOLD);
        welcomeTitle.setHorizontalAlignment(JLabel.CENTER);
        welcomeTitle.setVerticalAlignment(JLabel.CENTER);
        panel.add(welcomeTitle, BorderLayout.NORTH);


        JPanel center = new JPanel(new GridLayout(6, 1, 10, 10));
        center.setBackground(null);
        center.setBorder(BorderFactory.createEmptyBorder(22, 231, 17, 231));
        JTextField firstName = new JTextField("First Name");
        center.add(firstName);
        JTextField lastName = new JTextField("Last Name");
        center.add(lastName);
        JTextField email = new JTextField("Email");
        center.add(email);
        JPasswordField password = new JPasswordField("Password");
        center.add(password);
        JPasswordField confirmPassword = new JPasswordField("Confirm Password");
        center.add(confirmPassword);
        JButton createAcc = new JButton("Create Account", 45, 20);

        createAcc.addMouseListener(new MouseListener() {
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
                if (firstName.isEmpty()) {
                    new Alert("First Name cannot be empty", frame);
                    return;
                }
                if (lastName.isEmpty()) {
                    new Alert("Last Name cannot be empty", frame);
                    return;
                }
                if (email.isEmpty()) {
                    new Alert("Email cannot be empty", frame);
                    return;
                }

                if (!email.getText().endsWith("@iskolarngbayan.pup.edu.ph")) {
                    new Alert("Email must end with @iskolarngbayan.pup.edu.ph", frame);
                    return;
                }
                String passwordText = new String(password.getPassword());
                if (passwordText.isEmpty()) {
                    new Alert("Password cannot be empty", frame);
                    return;
                }
                if (passwordText.length()<6) {
                    new Alert("Password must contains at least 6 characters", frame);
                    return;
                }
                String confirmText = new String(confirmPassword.getPassword());
                if (confirmText.isEmpty()) {
                    new Alert("Please confirm password", frame);
                    return;
                }
                if (!passwordText.equals(confirmText)) {
                    new Alert("Password doesn't match", frame);
                    return;
                }
                User u = new User();
                u.setFirstName(firstName.getText());
                u.setLastName(lastName.getText());
                u.setEmail(email.getText());
                u.setPassword(passwordText);
                CreateUser create = new CreateUser(u, database);
                if (!create.isEmailUsed()) {
                    new Alert("Account created successfully!", frame);

                    LoadingScreen loadingScreen = new LoadingScreen();

                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() {
                            create.create();
                            u.setID(create.getUser().getID());
                            new Home(u, database);
                            return null;
                        }

                        @Override
                        protected void done() {
                            loadingScreen.close();
                            frame.dispose();
                        }
                    }.execute();

                } else {
                    new Alert("This email has been used before", frame);
                }
            }
        });

        center.add(createAcc);

        panel.add(center, BorderLayout.CENTER);

        JLabel login = new JLabel("Already have an account? Login", 20,
                GUIConstants.black, Font.PLAIN);
        login.addMouseListener(new MouseListener() {
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
                new Login(database);
                frame.dispose();
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