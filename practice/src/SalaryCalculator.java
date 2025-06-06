import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryCalculator extends JFrame implements ActionListener {
    private JTextField nameField, basicSalaryField, overtimeHoursField;
    private JButton calculateButton;
    private JTextArea outputArea;

    public SalaryCalculator() {
        setTitle("Employee Salary Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel Name, Basic Salary and Overtime Hours Input
        JPanel inputPanel = new JPanel(new GridLayout(5,2,5,5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Basic Salary:"));
        basicSalaryField = new JTextField();
        inputPanel.add(basicSalaryField);

        inputPanel.add(new JLabel("Overtime Worked:"));
        overtimeHoursField = new JTextField();
        inputPanel.add(overtimeHoursField);

        //add inputs to North Panel
        add(inputPanel, BorderLayout.NORTH);

        //Calculate Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        buttonPanel.add(calculateButton);

        //add button to Center Panel
        add(buttonPanel, BorderLayout.CENTER);

        //create text area for output
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //add text are to South Panel
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    //create action listener and validate inputs
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == calculateButton) {
            try {

                String name = nameField.getText();
                double basicSalary = Double.parseDouble(basicSalaryField.getText());
                int overtimeWorked = Integer.parseInt(overtimeHoursField.getText());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter employee name.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Employee employee = new Employee(name, basicSalary, overtimeWorked);
                displayDetails(employee);

            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Please enter valid input for basic salary and overtime.", "ERROR", JOptionPane.ERROR_MESSAGE);

            }

            nameField.setText("");
            basicSalaryField.setText("");
            overtimeHoursField.setText("");
        }
    }

    //display employee details
    public void displayDetails(Employee employee) {

        outputArea.append("Employee Name: " + employee.getName() + "\n");
        outputArea.append("Basic Salary: ₱" + employee.getBasicSalary() + "\n");
        outputArea.append("Overtime Pay: ₱" + employee.getOvertimePay() + "\n");
        outputArea.append("Gross Salary: ₱" + employee.getGrossSalary() + "\n");
        outputArea.append("-----------------------------\n");
    }

    public static void main(String[] args) {
        new SalaryCalculator();
    }
}
