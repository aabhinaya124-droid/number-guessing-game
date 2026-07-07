import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static int easyHighScore = Integer.MAX_VALUE;
    static int mediumHighScore = Integer.MAX_VALUE;
    static int hardHighScore = Integer.MAX_VALUE;

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Number Guessing Game");
        System.out.println("=================================");
        System.out.println("I am thinking of a number between 1 and 100.");

        boolean playAgain = true;

        while (playAgain) {

            int chances = chooseDifficulty();

            int number = random.nextInt(100) + 1;

            int attempts = 0;

            long startTime = System.currentTimeMillis();

            boolean guessed = false;

            while (chances > 0) {

                System.out.print("\nEnter your guess: ");
                int guess = sc.nextInt();

                attempts++;
                chances--;

                if (guess == number) {

                    long endTime = System.currentTimeMillis();

                    System.out.println("\nCongratulations!");
                    System.out.println("You guessed the correct number.");
                    System.out.println("Attempts : " + attempts);
                    System.out.println("Time Taken : " + ((endTime - startTime) / 1000.0) + " seconds");

                    updateHighScore(attempts);

                    guessed = true;
                    break;

                } else if (guess > number) {

                    System.out.println("Incorrect! The number is LESS than " + guess);

                } else {

                    System.out.println("Incorrect! The number is GREATER than " + guess);

                }

                if (chances == 1) {

                    System.out.println("Hint: The number is "
                            + (number % 2 == 0 ? "EVEN" : "ODD"));

                }

                System.out.println("Remaining Chances : " + chances);

            }

            if (!guessed) {

                System.out.println("\nGame Over!");
                System.out.println("The correct number was : " + number);

            }

            showHighScores();

            System.out.print("\nPlay Again? (Y/N): ");
            char choice = sc.next().toUpperCase().charAt(0);

            playAgain = choice == 'Y';
        }

        System.out.println("\nThanks for playing!");
    }

    static int currentDifficulty;

    static int chooseDifficulty() {

        System.out.println("\nSelect Difficulty");
        System.out.println("1. Easy (10 chances)");
        System.out.println("2. Medium (5 chances)");
        System.out.println("3. Hard (3 chances)");

        System.out.print("Enter choice: ");

        currentDifficulty = sc.nextInt();

        switch (currentDifficulty) {

            case 1:
                System.out.println("Easy Level Selected.");
                return 10;

            case 2:
                System.out.println("Medium Level Selected.");
                return 5;

            case 3:
                System.out.println("Hard Level Selected.");
                return 3;

            default:
                System.out.println("Invalid choice.");
                return 5;
        }
    }

    static void updateHighScore(int attempts) {

        switch (currentDifficulty) {

            case 1:
                easyHighScore = Math.min(easyHighScore, attempts);
                break;

            case 2:
                mediumHighScore = Math.min(mediumHighScore, attempts);
                break;

            case 3:
                hardHighScore = Math.min(hardHighScore, attempts);
                break;
        }
    }

    static void showHighScores() {

        System.out.println("\n------ High Scores ------");

        System.out.println("Easy   : "
                + (easyHighScore == Integer.MAX_VALUE ? "-" : easyHighScore));

        System.out.println("Medium : "
                + (mediumHighScore == Integer.MAX_VALUE ? "-" : mediumHighScore));

        System.out.println("Hard   : "
                + (hardHighScore == Integer.MAX_VALUE ? "-" : hardHighScore));
    }
}