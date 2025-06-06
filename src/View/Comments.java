package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Controller.CreateComment;
import Controller.ReadPostComments;
import Model.Database;
import Model.User;

public class Comments {

    public Comments(User user, Model.Post post, Database database) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);

        panel.add(new Post(user, post, database, frame));
        panel.add(Box.createVerticalStrut(7));

        JPanel newComment = new JPanel(new BorderLayout());
        newComment.setBackground(GUIConstants.white);
        Dimension dimension = new Dimension(500, 58);
        newComment.setPreferredSize(dimension);
        newComment.setMaximumSize(dimension);
        newComment.setMinimumSize(dimension);
        newComment.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));

        JTextArea commentIn = new JTextArea("Type a comment", 18, 5);
        newComment.add(new JScrollPane(commentIn), BorderLayout.CENTER);

        JButton commentBtn = new JButton("Post", 35, 16);
        commentBtn.setPreferredSize(new Dimension(81, 37));
        commentBtn.addMouseListener(new MouseListener() {
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
                if (commentIn.isEmpty()) {
                    new Alert("Cannot publish empty comment", frame);
                    return;
                }
                Model.Comment c = new Model.Comment(commentIn.getText(), user);
                if (new CreateComment(c, post, user, database).commented()) {
                    commentIn.setText("");
                    panel.add(new Comment(c));
                    panel.revalidate();
                }
            }
        });
        newComment.add(commentBtn, BorderLayout.EAST);

        panel.add(newComment);
        panel.add(Box.createVerticalStrut(7));

        ArrayList<Model.Comment> comments = new ReadPostComments(post, database)
                .getComments();
        for (Model.Comment c : comments) {
            panel.add(new Comment(c));
            panel.add(Box.createVerticalStrut(7));
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));

        buttonPanel.setBackground(null);
        JButton backBtn = new JButton("Back Home", 45,20);
        backBtn.setPreferredSize(new Dimension(170, 42));
        backBtn.addMouseListener(new MouseListener() {
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
                new Home(user, database);
                frame.dispose();
            }
        });

        buttonPanel.add(backBtn);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(panel));
        frame.setVisible(true);
        frame.requestFocus();
    }

}
