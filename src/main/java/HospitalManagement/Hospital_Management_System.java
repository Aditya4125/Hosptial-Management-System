package HospitalManagement;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

// Abstract class representing a Person
abstract class Person {
    protected String name;
    protected int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method overriding
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }

    // Abstract method
    abstract void displayInfo();
}

// Interface representing Medical Staff
interface MedicalStaff {
    void treatPatient();
}

// Class representing a Doctor
class Doctor extends Person implements MedicalStaff {
    private String specialization;

    // Constructor
    public Doctor(String name, int age, String specialization) {
        super(name, age);
        this.specialization = specialization;
    }

    // Method overriding
    @Override
    void displayInfo() {
        System.out.println("Doctor - " + super.toString() + ", Specialization: " + specialization);
    }

    // Implemented method from MedicalStaff interface
    public void treatPatient() {
        System.out.println("Doctor is treating patient...");
    }
}

// Class representing a Patient
class Patient extends Person {
    // Constructor
    public Patient(String name, int age) {
        super(name, age);
    }

    // Method overriding
    @Override
    void displayInfo() {
        System.out.println("Patient - " + super.toString());
    }
}

// Hospital class
class Hospital {
    private ArrayList<Person> staff;

    // Constructor
    public Hospital() {
        staff = new ArrayList<>();
    }

    // Method overloading
    public void addStaff(Person person) {
        staff.add(person);
    }

    public void addStaff(Doctor doctor) {
        staff.add(doctor);
    }

    // Method with lambda expression
    public void displayStaff() {
        staff.forEach(person -> System.out.println(person.toString()));
    }

    // Method to get all doctors
    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        for (Person person : staff) {
            if (person instanceof Doctor) {
                doctors.add((Doctor) person);
            }
        }
        return doctors;
    }

    // Method to get all patients
    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        for (Person person : staff) {
            if (person instanceof Patient) {
                patients.add((Patient) person);
            }
        }
        return patients;
    }
}

// Thread class representing treatment process
class TreatmentThread extends Thread {
    private Doctor doctor;
    private Patient patient;

    // Constructor
    public TreatmentThread(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
    }

    // Run method
    @Override
    public void run() {
        synchronized (doctor) {
            System.out.println("Doctor " + doctor.name + " is treating patient " + patient.name);
            doctor.treatPatient();
        }
    }
}

// Main class
public class HospitalSys {
    public static void main(String[] args) {
        // Creating Hospital object
        Hospital hospital = new Hospital();

        // GUI setup
        Frame frame = new Frame("Aditya's Hospital Management System");
        frame.setLayout(null); // Use null layout for manual component positioning

        // Labels
        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("Age:");
        Label specializationLabel = new Label("Specialization:");

        // Text fields
        TextField nameField = new TextField(20);
        TextField ageField = new TextField(20);
        TextField specializationField = new TextField(20);

        // Add Doctor button
        Button addButton = new Button("Add Doctor");
        addButton.setBounds(30, 140, 100, 30); // Set button bounds

        // Display Doctor button
        Button displayButton = new Button("Display Doctor");
        displayButton.setBounds(150, 140, 120, 30); // Set button bounds

        // ActionListener for Add Doctor button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String specialization = specializationField.getText();

                // Create new Doctor object and add to hospital
                Doctor newDoctor = new Doctor(name, age, specialization);
                hospital.addStaff(newDoctor);

                // Clear text fields
                nameField.setText("");
                ageField.setText("");
                specializationField.setText("");

                // Display updated staff info
                hospital.displayStaff();
            }
        });

        // ActionListener for Display Doctor button
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new frame to display doctors' information
                Frame displayFrame = new Frame("Doctors Information");
                displayFrame.setLayout(new BorderLayout()); // Use BorderLayout

                // Get all doctors from the hospital
                ArrayList<Doctor> doctors = hospital.getAllDoctors();

                // Create a TextArea to display doctors' info
                TextArea textArea = new TextArea();
                textArea.setEditable(false);

                // Display each doctor's information in the TextArea
                for (Doctor doctor : doctors) {
                    textArea.append(doctor.toString() + "\n");
                }

                // Add TextArea to the frame
                displayFrame.add(textArea, BorderLayout.CENTER);

                // Add WindowListener to handle the close button
                displayFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        displayFrame.dispose();
                    }
                });

                // Set frame properties
                displayFrame.setSize(300, 200);
                displayFrame.setVisible(true);
            }
        });

        // Add WindowListener to handle the close button for the main frame
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0); // Optional: Terminate the application when the main frame is closed
            }
        });

        // Add components to the main frame
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(ageLabel);
        frame.add(ageField);
        frame.add(specializationLabel);
        frame.add(specializationField);
        frame.add(addButton);
        frame.add(displayButton);

        // Set component bounds
        nameLabel.setBounds(30, 40, 60, 20);
        nameField.setBounds(100, 40, 150, 20);
        ageLabel.setBounds(30, 70, 60, 20);
        ageField.setBounds(100, 70, 150, 20);
        specializationLabel.setBounds(30, 100, 100, 20);
        specializationField.setBounds(130, 100, 150, 20);

        // Set main frame properties
        frame.setSize(300, 200);
        frame.setVisible(true);

        // Simulate treatment process with threads
        ArrayList<Doctor> doctors = hospital.getAllDoctors();
        ArrayList<Patient> patients = hospital.getAllPatients();

        for (Doctor doctor : doctors) {
            for (Patient patient : patients) {
                Thread treatmentThread = new TreatmentThread(doctor, patient);
                treatmentThread.start();
            }
        }
    }
}
