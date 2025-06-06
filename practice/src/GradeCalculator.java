import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GradeCalculator extends JFrame implements ActionListener {
    private JTextField studentCountField;
    private JButton startButton;
    private JLabel studentLabel;
    private JTextField[] gradeFields;
    private JButton nextButton;
    private JButton calculateButton;
    private JTextArea outputArea;

    private int totalStudents = 0;
    private int currentStudent = 1;
    private ArrayList<Student> studentList = new ArrayList<Student>();

    public GradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for input of number of students and for start button
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Number of Students:"));
        studentCountField = new JTextField(5);
        topPanel.add(studentCountField);
        startButton = new JButton("Start");
        topPanel.add(startButton);
        startButton.addActionListener(this);

        //add top panel to top
        add(topPanel, BorderLayout.NORTH);

        // Center panel for grades input
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        studentLabel = new JLabel("Student 1");
        studentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(studentLabel);

        JPanel gradesPanel = new JPanel(new GridLayout(5, 2, 5,5));
        gradesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gradeFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            gradesPanel.add(new JLabel("Grade " + (i + 1) + ":"));
            gradeFields[i] = new JTextField();
            gradesPanel.add(gradeFields[i]);
        }
        //add grades input to center panel
        centerPanel.add(gradesPanel);

        //assign next and calculate button
        nextButton = new JButton("Next");
        calculateButton = new JButton("Calculate");
        nextButton.setEnabled(false);
        calculateButton.setEnabled(false);
        nextButton.addActionListener(this);
        calculateButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(calculateButton);
        centerPanel.add(buttonPanel);

        // add center panel to center
        add(centerPanel, BorderLayout.CENTER);

        //create text area for output
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        //add text are to South Panel
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    //create action listener and validate inputs
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            try {
                totalStudents = Integer.parseInt(studentCountField.getText());
                if (totalStudents <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a number greater than 0.");
                    return;
                }
                startButton.setEnabled(false);
                studentCountField.setEnabled(false);
                nextButton.setEnabled(true);
                updateStudentLabel();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }

        if (e.getSource() == nextButton) {
            int[] grades = new int[5];
            try {
                for (int i = 0; i < 5; i++) {
                    grades[i] = Integer.parseInt(gradeFields[i].getText());
                    if (grades[i] < 0 || grades[i] > 100) {
                        throw new NumberFormatException();
                    }
                }
                studentList.add(new Student(grades));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter grades between 0 and 100 only.");
                return;
            }

            // Condition to stop inputs
            if(currentStudent == totalStudents) {
                nextButton.setEnabled(false);
                calculateButton.setEnabled(true);
                studentLabel.setText("Grade Input Finished");
                clearFields();
            } else {
                currentStudent++;
                updateStudentLabel();
                clearFields();
            }
        }

        // Output results
        if(e.getSource() == calculateButton) {
            double total = 0;
            outputArea.setText("");
            for(int i = 0; i < studentList.size(); i++) {
                double average = studentList.get(i).getAverage();
                outputArea.append("Student " + (i+1) + " Average: " + String.format("%.2f", average) + "\n");
                total += average;
            }
            double classAverage = total / studentList.size();
            outputArea.append("Overall Class Average: " + String.format("%.2f", classAverage) + "\n");
        }
    }

    // method to update student label (e.g. Student <1> to Student <2>)
    private void updateStudentLabel() {
        studentLabel.setText("Student " + currentStudent);
    }

    // method to clear text fields
    private void clearFields() {
        for(int i = 0; i < 5; i++) {
            gradeFields[i].setText("");
        }
    }

    public static void main(String[] args) {
        new GradeCalculator();
    }
}