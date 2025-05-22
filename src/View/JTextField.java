package View;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;

public class JTextField extends javax.swing.JTextField {

    private Shape shape;
    private String hint;

    public JTextField(String hint) {
        super();
        this.hint = hint;
        setFont(new Font("Segou UI", Font.BOLD, 18));
        setOpaque(false);
        setText(hint);
        setForeground(GUIConstants.textFieldHint);
        setBorder(BorderFactory.createEmptyBorder(TOP, 20, BOTTOM, 20));
        setPreferredSize(new Dimension(0, 40));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals("")) {
                    setText(hint);
                    setForeground(GUIConstants.textFieldHint);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setForeground(GUIConstants.black);
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        g.setColor(GUIConstants.white);
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(GUIConstants.white);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
    }

    public boolean contains(int x, int y) {
        if(shape == null || shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 40, 40);
        }
        return shape.contains(x,y);
    }

    public boolean isEmpty() {

        return getText().equals(hint) || getText().equals(" ");
    }
}
