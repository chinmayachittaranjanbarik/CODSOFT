package codsoft;
import java.util.*;

public class number_game { public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    ArrayList<Integer> scores = new ArrayList<>();
    boolean playAgain = true;

    System.out.println("Welcome to the Number Guessing Game!");

    while (playAgain) {
        int range = 100;
        int maxAttempts = 10;
        System.out.println("Select Difficulty Level:");
        System.out.println("1. Easy (1-50, 15 attempts)");
        System.out.println("2. Medium (1-100, 10 attempts)");
        System.out.println("3. Hard (1-200, 5 attempts)");
        System.out.print("Enter your choice (1/2/3): ");
        int difficulty = scanner.nextInt();

        switch (difficulty) {
            case 1:
                range = 50;
                maxAttempts = 15;
                break;
            case 2:
                range = 100;
                maxAttempts = 10;
                break;
            case 3:
                range = 200;
                maxAttempts = 5;
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Medium level.");
        }

        int numberToGuess = random.nextInt(range) + 1;
        int numberOfTries = 0;
        boolean win = false;
        long startTime = System.currentTimeMillis();

        System.out.println("I have picked a number between 1 and " + range + ". Try to guess it!");
        System.out.println("You have a maximum of " + maxAttempts + " attempts.");

        while (!win && numberOfTries < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            numberOfTries++;

            if (guess < 1 || guess > range) {
                System.out.println("Please enter a number between 1 and " + range + ".");
            } else if (guess < numberToGuess) {
                System.out.println("Too low!");
                giveHint(numberToGuess);
            } else if (guess > numberToGuess) {
                System.out.println("Too high!");
                giveHint(numberToGuess);
            } else {
                win = true;
                long endTime = System.currentTimeMillis();
                long timeTaken = (endTime - startTime) / 1000;
                System.out.println("Correct! You've guessed the number in " + numberOfTries + " tries and " + timeTaken + " seconds.");
                scores.add(numberOfTries);
            }

            if (numberOfTries == maxAttempts && !win) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess + ".");
            }
        }

        System.out.print("Do you want to play again? (yes/no): ");
        playAgain = scanner.next().equalsIgnoreCase("yes");
    }

    displayLeaderboard(scores);
    System.out.println("Thank you for playing! Goodbye.");
    scanner.close();
}

    private static void giveHint(int numberToGuess) {
        if (numberToGuess % 2 == 0) {
            System.out.println("Hint: The number is even.");
        } else {
            System.out.println("Hint: The number is odd.");
        }
        int multipleOf = findMultiple(numberToGuess);
        if (multipleOf > 1) {
            System.out.println("Hint: The number is a multiple of " + multipleOf + ".");
        }
    }

    private static int findMultiple(int number) {
        for (int i = 2; i <= 10; i++) {
            if (number % i == 0) {
                return i;
            }
        }
        return 1;
    }

    private static void displayLeaderboard(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            System.out.println("No scores to display.");
        } else {
            System.out.println("Leaderboard:");
            scores.sort(Integer::compareTo);
            for (int i = 0; i < scores.size(); i++) {
                System.out.println((i + 1) + ". " + scores.get(i) + " attempts");
            }
        }
    }
}

