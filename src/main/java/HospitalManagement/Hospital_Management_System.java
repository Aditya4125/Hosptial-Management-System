// Inside HospitalSys main method (add below existing code)

// Add Patient button
Button addPatientButton = new Button("Add Patient");
addPatientButton.setBounds(30, 180, 100, 30);

// Display Patients button
Button displayPatientsButton = new Button("Display Patients");
displayPatientsButton.setBounds(150, 180, 120, 30);

// Search Doctor button
Button searchDoctorButton = new Button("Search Doctor");
searchDoctorButton.setBounds(90, 220, 120, 30);

// Add Patient button action
addPatientButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());

            Patient newPatient = new Patient(name, age);
            hospital.addStaff(newPatient);

            nameField.setText("");
            ageField.setText("");
            specializationField.setText("");

            System.out.println("Patient Added!");
            hospital.displayStaff();
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Age entered!");
        }
    }
});

// Display Patients button action
displayPatientsButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        Frame displayFrame = new Frame("Patients Information");
        displayFrame.setLayout(new BorderLayout());

        ArrayList<Patient> patients = hospital.getAllPatients();

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        for (Patient patient : patients) {
            textArea.append(patient.toString() + "\n");
        }

        displayFrame.add(textArea, BorderLayout.CENTER);
        displayFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                displayFrame.dispose();
            }
        });

        displayFrame.setSize(300, 200);
        displayFrame.setVisible(true);
    }
});

// Search Doctor button action
searchDoctorButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String specialization = specializationField.getText().trim();
        Frame resultFrame = new Frame("Search Result");
        resultFrame.setLayout(new BorderLayout());

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        ArrayList<Doctor> doctors = hospital.getAllDoctors();
        boolean found = false;
        for (Doctor doctor : doctors) {
            if (doctor.toString().toLowerCase().contains(specialization.toLowerCase())) {
                textArea.append(doctor.toString() + "\n");
                found = true;
            }
        }

        if (!found) {
            textArea.append("No doctor found with specialization: " + specialization);
        }

        resultFrame.add(textArea, BorderLayout.CENTER);
        resultFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                resultFrame.dispose();
            }
        });

        resultFrame.setSize(350, 200);
        resultFrame.setVisible(true);
    }
});

// Add new buttons to the frame
frame.add(addPatientButton);
frame.add(displayPatientsButton);
frame.add(searchDoctorButton);

// Resize main frame to fit new buttons
frame.setSize(350, 300);
