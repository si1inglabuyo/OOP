package View;

public class JFrame extends javax.swing.JFrame {

    public JFrame() {
        super("Social Media Plaform");
        getContentPane().setBackground(GUIConstants.background);
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
