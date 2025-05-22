package SocialMediaPlatform;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tae extends Frame implements ActionListener {
    Button btnCompute = new Button("Compute");
    Button btnClear = new Button("Clear");

    Label lblMain = new Label("Computation of Grades");
    Label lbl1 = new Label("Prelims:");
    Label lbl2 = new Label("Mid Term:");
    Label lbl3 = new Label("Finals:");
    Label lbl4 = new Label("Final Rating:");
    Label lbl5 = new Label("Status:");

    TextField txtgrade1 = new TextField("",12);
    TextField txtgrade2 = new TextField("",12);
    TextField txtgrade3 = new TextField("",12);
    TextField txtanswer = new TextField("",12);
    TextField txtstatus = new TextField("",12);

    public tae() {
        setLayout(new FlowLayout());
        add(lblMain);
        add(lbl1); add(txtgrade1); add(lbl2); add(txtgrade2);
        add(lbl3); add(txtgrade3); add(lbl4); add(txtanswer);
        add(lbl5); add(txtstatus);

        add(btnCompute);
        btnCompute.addActionListener(this);
        add(btnClear);
        btnClear.addActionListener(this);

        txtanswer.setEditable(false);
        txtstatus.setEditable(false);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String stat = "";
        boolean ch1 = true;
        boolean ch2 = true;
        boolean ch3 = true;

        try {
            if(e.getSource() == btnCompute) {
                double prelims = Double.parseDouble(txtgrade1.getText());
                if((prelims <= 100) && (prelims > 0))
                    ch1 = true;
                else
                    ch1 = false;

                double midterms = Double.parseDouble(txtgrade2.getText());
                if((midterms <= 100) && (midterms > 0))
                    ch2 = true;
                else
                    ch2 = false;

                double finals = Double.parseDouble(txtgrade3.getText());
                if((finals <= 100) && (finals > 0))
                    ch3 = true;
                else
                    ch3 = false;

                if(ch1 && ch2 && ch3) {
                    double ans = (prelims * .30) + (midterms * .30) + (finals * .40);
                    txtanswer.setText(ans + "");
                    if(ans >= 75)
                        txtstatus.setText("PASSED");
                    else
                        txtstatus.setText("FAILED");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Input/s.");
                }
            } else if(e.getSource() == btnClear) {
                txtgrade1.setText("");
                txtgrade2.setText("");
                txtgrade3.setText("");
                txtanswer.setText("");
                txtstatus.setText("");
            }
        } catch(NumberFormatException er) {
            JOptionPane.showMessageDialog(null, "Invalid Input/s.");
            txtgrade1.setText("");
            txtgrade2.setText("");
            txtgrade3.setText("");
            txtanswer.setText("");
            txtstatus.setText("");
        }
    }

    public static void main(String[] args) {
        tae deptExam = new tae();
        deptExam.setSize(150,400);
        deptExam.setVisible(true);
    }
}
