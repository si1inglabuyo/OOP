package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import Controller.CreatePost;
import Controller.GenerateTimeline;
import Model.Database;
import Model.User;

public class Home {

    public Home(User user, Database database) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel sideBar = new JPanel();
        sideBar.setBackground(GUIConstants.background);
        Dimension sideBarDim = new Dimension(182, 1000);
        sideBar.setPreferredSize(sideBarDim);
        sideBar.setMaximumSize(sideBarDim);
        sideBar.setMinimumSize(sideBarDim);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.add(Box.createVerticalStrut(10));

        JPanel profile = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        profile.setMaximumSize(new Dimension(182, 50));
        profile.setBackground(GUIConstants.background);
        profile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profile.add(new JLabel(user.getName(), 19, GUIConstants.yellow, Font.BOLD));
        profile.addMouseListener(new MouseListener() {
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
                new Modify(user, database);
                frame.dispose();
            }
        });

        sideBar.add(profile);
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideButton("Posts", "myposts", user, database, frame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideButton("Comments", "mycomments", user, database, frame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideButton("Likes", "mylikes", user, database, frame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideButton("Friends", "friends", user, database, frame));
        sideBar.add(Box.createVerticalStrut(10));

        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/imgs/logout.png"));
        Image resizedDefault = defaultIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedDefaultIcon = new ImageIcon(resizedDefault);

        JLabel logoutLabel = new JLabel("Logout", 17, GUIConstants.white, Font.BOLD);
        logoutLabel.setIcon(resizedDefaultIcon);
        logoutLabel.setOpaque(true);
        logoutLabel.setBackground(GUIConstants.background);
        logoutLabel.setPreferredSize(new Dimension(200, 50));
        logoutLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        logoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutLabel.setHorizontalAlignment(JLabel.LEFT);

        logoutLabel.addMouseListener(new MouseListener() {
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
                logoutLabel.setBackground(GUIConstants.hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutLabel.setBackground(GUIConstants.background);
            }
        });

        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapperPanel.setBackground(GUIConstants.background);
        wrapperPanel.add(logoutLabel);
        sideBar.add(wrapperPanel);

        frame.getContentPane().add(sideBar, BorderLayout.WEST);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GUIConstants.white);
        Dimension dimension = new Dimension(500, 159);
        header.setPreferredSize(dimension);
        header.setMinimumSize(dimension);
        header.setMaximumSize(dimension);
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(null);
        north.add(new JLabel("Home", 20, GUIConstants.black, Font.BOLD),
                BorderLayout.WEST);
        header.add(north, BorderLayout.NORTH);

        JTextArea postIn = new JTextArea("Share your thoughts...", 18, 20);
        header.add(new JScrollPane(postIn), BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.setBackground(null);

        JButton postBtn = new JButton("Post", 35, 16);
        postBtn.addMouseListener(new MouseListener() {
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
                if (postIn.isEmpty()) {
                    new Alert("Cannot publish empty post", frame);
                    return;
                }
                Model.Post post = new Model.Post(postIn.getText(), user);
                if (new CreatePost(post, database).posted()) {
                    new Alert("Posted successfully", frame);
                    postIn.setText("");
                }
            }
        });
        postBtn.setPreferredSize(new Dimension(81, 37));
        south.add(postBtn);
        header.add(south, BorderLayout.SOUTH);

        panel.add(header);

        ArrayList<Model.Post> posts = new GenerateTimeline(user, database).getPosts();
        for (int i=0;i<posts.size();i++) {
            panel.add(Box.createVerticalStrut(7));
            panel.add(new Post(user, posts.get(i), database, frame));
        }

        frame.getContentPane().add(new JScrollPane(panel), BorderLayout.CENTER);
        frame.getContentPane().add(Box.createHorizontalStrut(182), BorderLayout.EAST);

        frame.setVisible(true);
        frame.requestFocus();


    }

    /**
     * Helper method to create a resized ImageIcon.
     *
     * @param path  Path to the image file.
     * @param width Desired width of the image.
     * @param height Desired height of the image.
     * @return Resized ImageIcon.
     */
    private ImageIcon createResizedIcon(java.net.URL path, int width, int height) {
        if (path == null) {
            System.err.println("Error: Image not found.");
            return null;
        }
        ImageIcon originalIcon = new ImageIcon(path);
        Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

}
