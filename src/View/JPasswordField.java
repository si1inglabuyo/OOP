package View;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class JPasswordField extends javax.swing.JPasswordField {

    private String placeholder;  // Placeholder text
    private boolean showingPlaceholder = true; // Tracks if placeholder is shown
    private boolean showingPassword = false;  // Tracks if password is visible
    private JLabel toggleLabel; // Toggle label for visibility

    public JPasswordField(String placeholder) {
        super();
        this.placeholder = placeholder;

        // Set the placeholder on initialization
        setPlaceholder();

        // General font and styling
        setOpaque(false); // Makes custom drawing visible
        setFont(new Font("SansSerif", Font.BOLD, 20));
        setForeground(GUIConstants.textFieldHint); // Placeholder color

        // Add toggle button for password visibility
        addToggleButton();

        // Focus listeners to handle placeholder showing/hiding
        addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (showingPlaceholder) {
                    clearPlaceholder(); // Clear placeholder when focused
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (getPassword().length == 0) { // Restore placeholder if field is empty
                    setPlaceholder();
                }
            }
        });

        // Add padding for the toggle icon
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 50));
    }

    private void setPlaceholder() {
        showingPlaceholder = true;
        setForeground(GUIConstants.textFieldHint); // Placeholder color
        setText(placeholder); // Set placeholder text
        setEchoChar((char) 0); // Disable masking for placeholder
    }

    private void clearPlaceholder() {
        showingPlaceholder = false;
        setForeground(GUIConstants.black); // Active text input color
        setText(""); // Clear the placeholder text
        setEchoChar('•'); // Enable masking for password
    }

    private void addToggleButton() {
        // Add a toggle label (with "eye" emoji)
        toggleLabel = new JLabel("👀",20, GUIConstants.background, Font.BOLD);
        toggleLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20)); // Emoji font
        toggleLabel.setForeground(Color.GRAY);
        toggleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleLabel.setPreferredSize(new Dimension(30, 30)); // Align and size properly

        // Add toggle visibility feature to the label on click
        toggleLabel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePasswordVisibility();
            }
        });

        // Place toggle label on the right side of the field
        setLayout(new BorderLayout());
        add(toggleLabel, BorderLayout.EAST);
    }

    private void togglePasswordVisibility() {
        if (showingPassword) {
            setEchoChar(showingPlaceholder ? (char) 0 : '•'); // Restore masking if needed
            toggleLabel.setText("👀"); // Set toggle to show mode
        } else {
            setEchoChar((char) 0); // Display password as plain text
            toggleLabel.setText("🙈"); // Set toggle to hide mode
        }
        showingPassword = !showingPassword; // Update visibility state
    }

    @Override
    public char getEchoChar() {
        // Override masking logic to handle placeholder
        return showingPlaceholder ? (char) 0 : super.getEchoChar();
    }

    public boolean isEmpty() {
        // Helper method to check if field is empty or showing placeholder
        return showingPlaceholder || getPassword().length == 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Custom background with rounded corners
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(GUIConstants.white); // Background color
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45); // Rounded rectangle
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Custom border with rounded edges
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2)); // Border thickness
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
    }
}