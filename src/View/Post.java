package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import Controller.DislikePost;
import Controller.LikePost;
import Controller.ReadPostComments;
import Controller.ReadPostLikes;
import Model.Database;
import Model.User;

@SuppressWarnings("serial")
public class Post extends JPanel {

    private JLabel likesCounter;
    private Model.Post post;
    private Database database;

    public Post(User u, Model.Post post, Database database, JFrame f) {
        this.post = post;
        this.database = database;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GUIConstants.white);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 25));

        // Header Section
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(null);
        JLabel author = new JLabel(post.getUser().getName(), 20, GUIConstants.post, Font.BOLD);
        header.add(author, BorderLayout.WEST);
        JLabel date = new JLabel(post.getDateToString(), 15, GUIConstants.post, Font.PLAIN);
        header.add(date, BorderLayout.EAST);

        add(header);
        add(Box.createVerticalStrut(7));

        // Content Section
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEADING));
        center.setBackground(null);
        JTextArea content = new JTextArea(post.getContent(), 18, GUIConstants.post, Font.PLAIN);
        center.add(content);
        add(center);
        add(Box.createVerticalStrut(7));

        // Bottom Section
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(null);

        JPanel likes = new JPanel(new FlowLayout(FlowLayout.LEFT, 13, 13));
        likes.setBackground(null);

        // Load and Resize Like Icons
        ImageIcon likeIcon = createResizedIcon(getClass().getResource("/imgs/like.png"), 25, 25);
        ImageIcon likedIcon = createResizedIcon(getClass().getResource("/imgs/liked.png"), 25, 25);

        ImageIcon icon = u.liked(post) ? likedIcon : likeIcon;
        javax.swing.JLabel likeButton = new javax.swing.JLabel(icon);

        likeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        likeButton.addMouseListener(new MouseListener() {
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
                if (!u.liked(post)) {
                    if (new LikePost(u, post, database).isLiked()) {
                        u.like(post);
                        likeButton.setIcon(likedIcon);
                    } else {
                        new Alert("Failed to like the post. Please try again.", null);
                    }
                } else {
                    if (new DislikePost(u, post, database).isDisiked()) {
                        u.dislike(post);
                        likeButton.setIcon(likeIcon);
                    } else {
                        new Alert("Failed to dislike the post. Please try again.", null);
                    }
                }
                refreshLikesCounter();
            }
        });
        likes.add(likeButton);

        // Likes Counter
        likesCounter = new JLabel("", 15, GUIConstants.textFieldHint, Font.BOLD);
        refreshLikesCounter();
        likes.add(likesCounter);
        bottom.add(likes, BorderLayout.WEST);

        // Comments Section
        int commentsCount = new ReadPostComments(post, database).getCommentsCount();
        JLabel comments = new JLabel(
                commentsCount + (commentsCount < 2 ? " Comment" : " Comments"),
                15,
                GUIConstants.textFieldHint,
                Font.BOLD
        );
        comments.setCursor(new Cursor(Cursor.HAND_CURSOR));
        comments.addMouseListener(new MouseListener() {
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
                new Comments(u, post, database);
                f.dispose();
            }
        });

        bottom.add(comments, BorderLayout.EAST);
        add(bottom);

        // Set Dimensions
        int height = (int) (115 + content.getPreferredSize().getHeight());
        Dimension dimension = new Dimension(500, height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }

    /**
     * Refresh the likes counter from the database.
     */
    private void refreshLikesCounter() {
        int likesCount = new ReadPostLikes(post, database).getLikesCount();
        likesCounter.setText(likesCount + (likesCount < 2 ? " Like" : " Likes"));
    }

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