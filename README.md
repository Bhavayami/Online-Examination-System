# Online Examination System

A **Java Swing-based Online Examination System** that provides a timed multiple-choice exam with **10 questions**. Users can navigate between questions, select and save answers, submit manually or automatically when time expires, and receive a result showing correct, wrong, unanswered answers, percentage, and pass/fail status.

## Overview

The application presents **10 Java-related multiple-choice questions** through a graphical user interface. Each question provides **four possible answers**, with only one option selectable at a time.

The examination is limited to **60 seconds**. Users can navigate forward and backward while their selected answers are preserved. When the exam is submitted, the application evaluates the responses and presents a detailed result through a dialog box.

## Main Functionality

### Question Handling

- Contains **10 predefined multiple-choice questions**.
- Each question has **four answer options**.
- Questions are stored using an `ArrayList`.
- The `Question` class stores:
  - The question text
  - Four answer options
  - The correct answer

### Exam Navigation

- The **Next** button moves to the following question.
- The **Previous** button returns to the previous question.
- The **Previous** button is disabled on the first question.
- The **Next** button is disabled on the final question.
- Previously selected answers are restored when navigating between questions.

### Answer Selection

The application uses four `JRadioButton` components grouped with a `ButtonGroup`.

This ensures that **only one answer can be selected at a time** for each question.

### Time Control

A **Swing Timer** controls the examination duration.

- **Starting time:** 60 seconds
- The timer updates every second.
- The remaining time is displayed at the top of the window.
- When the timer reaches zero, the exam is **automatically submitted**.

### Result Processing

After submission, the system checks the user's selected answers against the correct answers stored in each `Question` object.

The result includes:

- **Total number of questions**
- **Number of correct answers**
- **Number of wrong answers**
- **Number of unanswered questions**
- **Percentage obtained**
- **Pass/Fail result**

The passing condition implemented in the program is **40% or above**.

## User Interface

The graphical interface is created using **Java Swing components**, including:

| Component | Role |
|---|---|
| `JFrame` | Main examination window |
| `JLabel` | Displays the question and remaining time |
| `JRadioButton` | Provides answer choices |
| `ButtonGroup` | Allows one answer to be selected |
| `JButton` | Handles Previous, Next, and Submit actions |
| `JPanel` | Organizes the interface |
| `JOptionPane` | Displays warnings, confirmation, and final results |

## Project Structure

```text
Online Examination System
│
├── OnlineExam.java
└── Question.java

OnlineExam.java
Contains the main application logic, including:

The graphical interface

Question navigation

Answer storage

Timer management

Submission process

Result calculation

Question.java
Defines the Question class and stores:

The question statement

Four answer choices

The correct answer

Getter methods are provided to retrieve these values.

Running the Application
Make sure Java is installed on your system.

Step 1: Compile the Source Files
Open a terminal in the project directory and run:
javac Question.java OnlineExam.java

Step 2: Start the Application
java OnlineExam

The program launches the examination window through the main() method.

Examination Flow
Start Application
       │
       ▼
Load Questions
       │
       ▼
Display First Question
       │
       ▼
Answer Questions
       │
       ├──── Previous / Next ────┐
       │                         │
       └─────────────────────────┘
       │
       ▼
Submit / Timer Expires
       │
       ▼
Evaluate Answers
       │
       ▼
Calculate Percentage
       │
       ▼
Display Final Result
Concepts Demonstrated
This project provides practical implementation of several Java programming concepts:

Object-Oriented Programming

Classes and Constructors

Encapsulation

Getter Methods

ArrayList

Java Swing GUI Development

Event Handling with ActionListener

JRadioButton and ButtonGroup

Swing Timer

Conditional Statements

Arrays for Storing User Responses

Loops for Result Calculation

Dialog Boxes using JOptionPane

Project Objective
The project demonstrates how core Java programming concepts can be combined to create a functional desktop-based examination application.

It focuses on:

GUI development

User interaction

Data management

Timer-based execution

Automated evaluation

The project provides a simple and interactive way for users to answer multiple-choice questions, navigate through the examination, and receive their results automatically after submission or when the examination time expires.
