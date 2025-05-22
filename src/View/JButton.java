package View;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JButton extends JLabel{

    private Shape shape;
    private int radius;
    private int gap;
    private Insets margin;

    public JButton(String text, int radius, int textSize) {
        super(text);
        this.radius = radius;
        setFont(new Font("Segoe UI", Font.BOLD, textSize));
        setOpaque(false);
        setForeground(GUIConstants.white);
        setHorizontalAlignment(CENTER);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    protected void paintComponent(Graphics g) {
        g.setColor(GUIConstants.blue);
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(GUIConstants.blue);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
    }

    public boolean contains(int x, int y) {
        if(shape==null || shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 10, 10);
        }
        return shape.contains(x,y);
    }


}
