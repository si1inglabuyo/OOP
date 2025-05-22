package View;

import javax.swing.*;
import java.awt.*;

public class JLabel extends javax.swing.JLabel{

    public JLabel(String text, int textSize, Color color, int style) {
        setFont(new Font("Segoe UI", style, textSize));
        setText(text);
        setForeground(color);
    }

    public JLabel(ImageIcon icon) {
        setIcon(icon);
    }
}
