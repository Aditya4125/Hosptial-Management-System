# Hospital Management System

## Overview
The **Hospital Management System** is a Java-based application that simulates hospital operations, including managing doctors, patients, and treatment processes. The project demonstrates key object-oriented programming (OOP) concepts, Java collections, threading, and graphical user interface (GUI) programming using **Abstract Window Toolkit (AWT)**.

## Features
- **Abstract Class (`Person`)**: Defines a common structure for `Doctor` and `Patient`.
- **Interface (`MedicalStaff`)**: Ensures doctors implement the `treatPatient()` method.
- **Inheritance (`Doctor` & `Patient`)**: Enables reusability of the `Person` class.
- **Method Overriding (`displayInfo()`)**: Provides specific implementations for `Doctor` and `Patient`.
- **Method Overloading (`addStaff()`)**: Allows adding different types of staff to the hospital.
- **ArrayList (`Hospital`)**: Stores the list of staff dynamically.
- **Threads (`TreatmentThread`)**: Simulates concurrent treatment processes.
- **AWT GUI**: Provides a graphical interface for adding and displaying doctors.
- **Event Handling (`ActionListener`)**: Manages button click actions.
- **Window Handling (`WindowAdapter`)**: Ensures proper closing of application windows.

## Classes and Their Roles
### 1. **Abstract Class: `Person`**
- Represents a person with `name` and `age`.
- Contains an abstract method `displayInfo()`.
- Implemented by `Doctor` and `Patient` classes.

### 2. **Interface: `MedicalStaff`**
- Defines the method `treatPatient()`.
- Implemented by `Doctor` class.

### 3. **Inheritance: `Doctor` and `Patient`**
- Both inherit from `Person`.
- `Doctor` implements `MedicalStaff` to provide `treatPatient()` functionality.

### 4. **Method Overriding (`displayInfo()`)**
- `Doctor` and `Patient` provide their own implementation of `displayInfo()`.

### 5. **Method Overloading (`addStaff()`)**
- `Hospital` class has overloaded methods to add `Person` or `Doctor` objects.

### 6. **ArrayList Usage (`Hospital`)**
- Stores hospital staff dynamically.
- Methods available to retrieve doctors and patients.

### 7. **Multithreading (`TreatmentThread`)**
- Each doctor treats a patient in a separate thread.

### 8. **AWT GUI Implementation**
- `Frame`, `Label`, `TextField`, `Button`, and `TextArea` used for UI.
- GUI allows adding doctors and displaying doctor details.

### 9. **Event Handling (`ActionListener`)**
- Handles button actions for adding and displaying doctors.

### 10. **Window Handling (`WindowAdapter`)**
- Ensures proper window closing operations.

## Installation & Usage
### **Prerequisites**
- Java JDK 8 or later
- Any Java IDE (Eclipse, IntelliJ IDEA, or VS Code)

### **Steps to Run**
1. Clone this repository or copy the files.
2. Open the project in an IDE.
3. Compile and run the `HospitalSys.java` file.
4. Use the GUI to add and display doctors.

## Example Usage
### **Adding a Doctor**
1. Enter doctor’s name, age, and specialization.
2. Click **Add Doctor**.
3. Doctor is added to the hospital's staff list.

### **Displaying Doctors**
1. Click **Display Doctor**.
2. A new window opens with a list of all added doctors.
