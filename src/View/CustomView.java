package View;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import Controller.ReadAllUsers;
import Controller.ReadUserComments;
import Controller.ReadUserLikes;
import Controller.ReadUserPosts;
import Model.Database;
import Model.Post;
import Model.User;

public class CustomView {

    public CustomView(String view, User user, Database database) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GUIConstants.white);
        Dimension dimension = new Dimension(500, 50);
        header.setPreferredSize(dimension);
        header.setMaximumSize(dimension);
        header.setMinimumSize(dimension);
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(null);
        north.add(new JLabel("Your " + view, 20, GUIConstants.black, Font.BOLD),
                BorderLayout.WEST);

        header.add(north, BorderLayout.NORTH);

        panel.add(header);

        // Show Loading Screen and Perform Content Loading
        LoadingScreen loadingScreen = new LoadingScreen();

        // Use SwingWorker to load data in the background
        new SwingWorker<ArrayList<?>, Void>() {
            @Override
            protected ArrayList<?> doInBackground() {
                switch (view) {
                    case "Friends":
                        return new ReadAllUsers(database, user).getList();
                    case "Posts":
                        return new ReadUserPosts(user, database).getPosts();
                    case "Comments":
                        return new ReadUserComments(user, database, frame)
                                .getPostsWithComments();
                    case "Likes":
                        return new ReadUserLikes(user, database).getPosts();
                    default:
                        return null;
                }
            }

            @Override
            protected void done() {
                try {
                    loadingScreen.close(); // Close the loading screen

                    // Handle the result and dynamically render content
                    Object result = get();
                    if (result instanceof ArrayList<?>) {
                        ArrayList<?> list = (ArrayList<?>) result;
                        if (view.equals("Friends")) {
                            for (Object o : list) {
                                if (o instanceof User) {
                                    panel.add(Box.createVerticalStrut(7));
                                    panel.add(new Friend(user, database, (User) o));
                                }
                            }
                        } else if (view.equals("Posts") || view.equals("Likes")) {
                            for (Object o : list) {
                                if (o instanceof Post) {
                                    panel.add(Box.createVerticalStrut(7));
                                    panel.add(new View.Post(user, (Post) o, database, frame));
                                }
                            }
                        } else if (view.equals("Comments")) {
                            for (Object o : list) {
                                if (o instanceof JPanel) {
                                    panel.add(Box.createVerticalStrut(7));
                                    panel.add((JPanel) o);
                                }
                            }
                        }
                        panel.revalidate();
                        panel.repaint();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    new Alert("Error loading content. Please try again.", frame);
                }
            }
        }.execute();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));

        buttonPanel.setBackground(null);
        JButton backBtn = new JButton("Back Home", 45, 20);
        backBtn.setPreferredSize(new Dimension(170, 42));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoadingScreen loadingscreen = new LoadingScreen();
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        // Show the Home screen
                        new Home(user, database);
                        return null;
                    }

                    @Override
                    protected void done() {
                        loadingscreen.close();
                        frame.dispose(); // Close the current frame
                    }
                }.execute();
            }
        });

        buttonPanel.add(backBtn);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(new JScrollPane(panel));
        frame.setVisible(true);
        frame.requestFocus();
    }
}