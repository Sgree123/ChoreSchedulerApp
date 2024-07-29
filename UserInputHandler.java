import java.util.Scanner;

public class UserInputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String message) {
        int input;
        do {
            System.out.print(message);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input <= 0);
        scanner.nextLine(); // Consume newline
        return input;
    }

    public static String getStringInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static String getYesOrNoInput(String message) {
        String input;
        do {
            System.out.print(message);
            input = scanner.nextLine().trim().toLowerCase();
        } while (!input.equals("y") && !input.equals("n"));
        return input;
    }

    
}//User input handler class was created with help from Reddit
