import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StudentGradeCalculatorGUI {
    private static int noOfSubjects;
    private static double[] marks;
    private static double totMarks;
    private static double avgMarks;
    private static double percentages;
    private static String grade;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350); 

        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBackground(new Color(245, 245, 245)); 
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        // Label for number of subjects
        JLabel subjectLabel = new JLabel("Number of Subjects:");
        subjectLabel.setBounds(10, 20, 150, 25);
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 14));
        subjectLabel.setForeground(new Color(50, 50, 50)); 
        panel.add(subjectLabel);

        // TextField for number of subjects
        JTextField subjectText = new JTextField(20);
        subjectText.setBounds(160, 20, 165, 25);
        subjectText.setFont(new Font("Arial", Font.PLAIN, 14));
        subjectText.setBackground(new Color(230, 230, 230)); 
        subjectText.setForeground(Color.BLACK); 
        panel.add(subjectText);

        // Label for marks input
        JLabel marksLabel = new JLabel("Enter Marks (comma separated):");
        marksLabel.setBounds(10, 50, 200, 25);
        marksLabel.setFont(new Font("Arial", Font.BOLD, 14));
        marksLabel.setForeground(new Color(50, 50, 50)); 
        panel.add(marksLabel);

        // TextField for marks input
        JTextField marksText = new JTextField(20);
        marksText.setBounds(210, 50, 165, 25);
        marksText.setFont(new Font("Arial", Font.PLAIN, 14));
        marksText.setBackground(new Color(230, 230, 230)); 
        marksText.setForeground(Color.BLACK); 
        panel.add(marksText);

        // Calculate Grade button
        JButton calculateButton = new JButton("Calculate Grade");
        calculateButton.setBounds(10, 80, 150, 25);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(70, 130, 180)); 
        calculateButton.setForeground(Color.WHITE); 
        calculateButton.setFocusPainted(false); 
        calculateButton.setBorder(BorderFactory.createRaisedBevelBorder()); 
        panel.add(calculateButton);

        // Result label for grade
        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(10, 110, 350, 25);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 128, 0)); 
        panel.add(resultLabel);

        // Button Action Listener
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get number of subjects
                    noOfSubjects = Integer.parseInt(subjectText.getText());

                    // Get marks from the text field
                    String[] marksArray = marksText.getText().split(",");
                    marks = new double[noOfSubjects];
                    totMarks = 0;

                    // Parse marks and calculate total
                    for (int i = 0; i < noOfSubjects; i++) {
                        marks[i] = Double.parseDouble(marksArray[i].trim());
                        totMarks += marks[i];
                    }

                    // Calculate average and percentages
                    avgMarks = totMarks / noOfSubjects;
                    percentages = avgMarks;

                    // Determine grade based on percentages
                    if (percentages > 90) {
                        grade = "O";
                    } else if (percentages > 80) {
                        grade = "A+";
                    } else if (percentages > 70) {
                        grade = "A";
                    } else if (percentages > 60) {
                        grade = "B+";
                    } else if (percentages > 50) {
                        grade = "B";
                    } else if (percentages > 40) {
                        grade = "C";
                    } else {
                        grade = "F";
                    }

                    // Display the result
                    if (grade.equals("F")){
                        resultLabel.setText("You got " + grade + " grade.");
                        resultLabel.setForeground(Color.RED); 
                    }else {
                        resultLabel.setText("You got " + grade + " grade.");
                        resultLabel.setForeground(Color.GREEN);
                    }
                } catch (Exception ex) {
                    // Handle invalid input
                    resultLabel.setText("Invalid input. Please enter valid data.");
                    resultLabel.setForeground(new Color(255, 0, 0)); 
                }
            }
        });
    }
}
