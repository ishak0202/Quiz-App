import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
        // Add questions to the quiz
        questions.add(new Question("What is the capital of France?", List.of("A. Paris", "B. Berlin", "C. Madrid", "D. Rome"), 0));
        questions.add(new Question("Which programming language is Java based on?", List.of("A. C++", "B. Python", "C. Smalltalk", "D. JavaScript"), 2));
        // Add more questions as needed
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

public class OnlineQuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();
        int totalQuestions = quiz.getQuestions().size();
        int score = 0;

        System.out.println("Welcome to the Online Quiz App!");

        for (int i = 0; i < totalQuestions; i++) {
            Question currentQuestion = quiz.getQuestions().get(i);

            // Display the current question
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getQuestionText());
            for (String option : currentQuestion.getOptions()) {
                System.out.println(option);
            }

            // Get user input and validate it
            int userChoice = getUserChoice(scanner, currentQuestion.getOptions().size());

            // Check if the user's choice is correct
            if (userChoice == currentQuestion.getCorrectOptionIndex()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " +
                        currentQuestion.getOptions().get(currentQuestion.getCorrectOptionIndex()) + "\n");
            }
        }

        // Display the user's score
        System.out.println("Quiz completed! Your score: " + score + "/" + totalQuestions);

        // Close the scanner
        scanner.close();
    }

    private static int getUserChoice(Scanner scanner, int maxOptions) {
        int userChoice = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Enter your choice (1-" + maxOptions + "): ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice >= 1 && userChoice <= maxOptions) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and " + maxOptions + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }

        return userChoice - 1; // Adjust to zero-based index
    }
}

