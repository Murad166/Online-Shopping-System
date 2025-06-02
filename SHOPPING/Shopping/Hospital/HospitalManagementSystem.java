import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Patient {
    String name;
    int age;
    String disease;

    public Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Disease: " + disease;
    }
}

public class HospitalManagementSystem {
    private JFrame frame;
    private JTextField nameField, ageField, diseaseField;
    private JTextArea outputArea;
    private ArrayList<Patient> patients = new ArrayList<>();

    public HospitalManagementSystem() {
        frame = new JFrame("Hospital Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Top panel (form inputs)
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("Patient Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Disease:"));
        diseaseField = new JTextField();
        inputPanel.add(diseaseField);

        JButton addBtn = new JButton("Add Patient");
        inputPanel.add(addBtn);

        JButton viewBtn = new JButton("View All Patients");
        inputPanel.add(viewBtn);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Center panel (output area)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button actions
        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String disease = diseaseField.getText().trim();

            if (name.isEmpty() || ageText.isEmpty() || disease.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                Patient p = new Patient(name, age, disease);
                patients.add(p);
                outputArea.append("Added: " + p + "\n");
                nameField.setText("");
                ageField.setText("");
                diseaseField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Age must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        viewBtn.addActionListener(e -> {
            outputArea.setText("All Patients:\n");
            for (Patient p : patients) {
                outputArea.append(p + "\n");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HospitalManagementSystem::new);
    }
}

