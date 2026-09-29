import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OnlineExam extends JFrame implements ActionListener {

    // ArrayList to store questions
    ArrayList<Question> questions = new ArrayList<Question>();

    // GUI components
    JLabel questionLabel;
    JLabel timerLabel;

    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;

    ButtonGroup buttonGroup;

    JButton previousButton;
    JButton nextButton;
    JButton submitButton;

    // To store selected answers
    int[] userAnswers = new int[10];

    // Current question number
    int currentQuestion = 0;

    // Timer
    Timer timer;
    int timeLeft = 60;

    public OnlineExam() {

        // Set frame properties
        setTitle("Online Examination System");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add questions
        addQuestions();

        // Initially no answers selected
        for (int i = 0; i < 10; i++) {
            userAnswers[i] = -1;
        }

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Timer label
        timerLabel = new JLabel("Time Left: 60 seconds");
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));

        mainPanel.add(timerLabel, BorderLayout.NORTH);

        // Question panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(5, 1));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        // ButtonGroup allows only one option
        buttonGroup = new ButtonGroup();

        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);

        questionPanel.add(questionLabel);
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(option3);
        questionPanel.add(option4);

        mainPanel.add(questionPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit");

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        submitButton.addActionListener(this);

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Display first question
        displayQuestion();

        // Start timer
        startTimer();

        setVisible(true);
    }

    // Add 10 questions
    public void addQuestions() {

        questions.add(new Question(
                "1. Which language is used for Java programming?",
                "Python",
                "Java",
                "C",
                "HTML",
                2));

        questions.add(new Question(
                "2. Which keyword is used to create a class in Java?",
                "class",
                "Class",
                "create",
                "new",
                1));

        questions.add(new Question(
                "3. Which collection is used to store multiple objects?",
                "ArrayList",
                "Scanner",
                "JFrame",
                "JButton",
                1));

        questions.add(new Question(
                "4. Which component is used for a single-choice option?",
                "JTextField",
                "JLabel",
                "JRadioButton",
                "JFrame",
                3));

        questions.add(new Question(
                "5. Which keyword is used to inherit a class?",
                "implements",
                "extends",
                "inherit",
                "super",
                2));

        questions.add(new Question(
                "6. Which method is the starting point of a Java program?",
                "start()",
                "run()",
                "main()",
                "begin()",
                3));

        questions.add(new Question(
                "7. Which class is used to create a window in Swing?",
                "JFrame",
                "JWindow",
                "JPanel",
                "JButton",
                1));

        questions.add(new Question(
                "8. Which keyword is used to create an object?",
                "object",
                "create",
                "new",
                "this",
                3));

        questions.add(new Question(
                "9. Which Swing component displays a button?",
                "JLabel",
                "JButton",
                "JRadioButton",
                "JTextArea",
                2));

        questions.add(new Question(
                "10. Which class is used to display a popup message?",
                "JOptionPane",
                "JMessage",
                "JDialogBox",
                "JPopup",
                1));
    }

    // Display current question
    public void displayQuestion() {

        Question q = questions.get(currentQuestion);

        questionLabel.setText(q.getQuestion());

        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());

        // Remove previous selection
        buttonGroup.clearSelection();

        // Restore previously selected answer
        if (userAnswers[currentQuestion] == 1) {
            option1.setSelected(true);
        } 
        else if (userAnswers[currentQuestion] == 2) {
            option2.setSelected(true);
        } 
        else if (userAnswers[currentQuestion] == 3) {
            option3.setSelected(true);
        } 
        else if (userAnswers[currentQuestion] == 4) {
            option4.setSelected(true);
        }

        // Previous button disabled on first question
        if (currentQuestion == 0) {
            previousButton.setEnabled(false);
        } 
        else {
            previousButton.setEnabled(true);
        }

        // Next button disabled on last question
        if (currentQuestion == 9) {
            nextButton.setEnabled(false);
        } 
        else {
            nextButton.setEnabled(true);
        }
    }

    // Store selected answer
    public void saveAnswer() {

        if (option1.isSelected()) {
            userAnswers[currentQuestion] = 1;
        } 
        else if (option2.isSelected()) {
            userAnswers[currentQuestion] = 2;
        } 
        else if (option3.isSelected()) {
            userAnswers[currentQuestion] = 3;
        } 
        else if (option4.isSelected()) {
            userAnswers[currentQuestion] = 4;
        }
    }

    // Start 60 second timer
    public void startTimer() {

        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                timeLeft--;

                timerLabel.setText("Time Left: " + timeLeft + " seconds");

                if (timeLeft <= 0) {

                    timer.stop();

                    saveAnswer();

                    JOptionPane.showMessageDialog(
                            OnlineExam.this,
                            "Time is over! The exam will be submitted automatically.",
                            "Time Over",
                            JOptionPane.WARNING_MESSAGE
                    );

                    submitExam();
                }
            }
        });

        timer.start();
    }

    // Calculate result
    public void submitExam() {

        saveAnswer();

        int correct = 0;
        int wrong = 0;

        for (int i = 0; i < questions.size(); i++) {

            if (userAnswers[i] == -1) {
                continue;
            }

            if (userAnswers[i] == questions.get(i).getCorrectAnswer()) {
                correct++;
            } 
            else {
                wrong++;
            }
        }

        int totalQuestions = questions.size();

        double percentage = ((double) correct / totalQuestions) * 100;

        String result;

        if (percentage >= 40) {
            result = "PASS";
        } 
        else {
            result = "FAIL";
        }

        String message =
                "===== EXAM RESULT =====\n\n"
                + "Total Questions: " + totalQuestions + "\n"
                + "Correct Answers: " + correct + "\n"
                + "Wrong Answers: " + wrong + "\n"
                + "Unanswered: " + (totalQuestions - correct - wrong) + "\n"
                + "Percentage: " + percentage + "%\n"
                + "Result: " + result;

        JOptionPane.showMessageDialog(
                this,
                message,
                "Final Result",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Close exam window
        dispose();
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nextButton) {

            saveAnswer();

            if (currentQuestion < 9) {
                currentQuestion++;
                displayQuestion();
            }
        }

        else if (e.getSource() == previousButton) {

            saveAnswer();

            if (currentQuestion > 0) {
                currentQuestion--;
                displayQuestion();
            }
        }

        else if (e.getSource() == submitButton) {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to submit the exam?",
                    "Confirm Submission",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                timer.stop();
                submitExam();
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        new OnlineExam();
    }
}