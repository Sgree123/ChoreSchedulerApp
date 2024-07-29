import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ChoreSchedulerApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Household household;
    private static ChoreList choreList;
    private static Schedule schedule;

    public static void main(String[] args) {
        System.out.println("Welcome to Chore Scheduler App!");

        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice(1, 6); // Adjusted to the number of options in the main menu
            switch (choice) {
                case 1:
                    createHousehold();
                    break;
                case 2:
                    addChores();
                    break;
                case 3:
                    assignChores();
                    break;
                case 4:
                    generateSchedule();
                    break;
                case 5:
                    viewSchedule();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using Chore Scheduler App. Goodbye!");
                    break;
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
    if (household == null) {
        System.out.println("1. Create Household");
    } else {
        System.out.println("1. Replace Household");
    }
    System.out.println("2. Add Chores");
    System.out.println("3. Assign Chores");
    System.out.println("4. Generate Schedule");
    System.out.println("5. Show Schedule");
    System.out.println("6. Log Out");
    System.out.print("Select an option: ");
}

private static int getUserChoice(int min, int max) {
    int choice;
    do {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        choice = scanner.nextInt();
    } while (choice < min || choice > max);
    scanner.nextLine(); // Consume newline
    return choice;
}

    private static void createHousehold() {
        if (schedule != null && !schedule.getScheduleMap().isEmpty()) {
            System.out.println("Chores have already been assigned to members. Cannot edit household now.");
            return;
        }

        System.out.print("Enter household name: ");
        String householdName = scanner.nextLine();
        household = new Household(householdName);
        System.out.println("Household created: " + householdName);
        addMembers();
    }

    private static void addMembers() {
        int numMembers = UserInputHandler.getIntInput("Enter the number of members to add: ");
        for (int i = 0; i < numMembers; i++) {
            String memberName = UserInputHandler.getStringInput("Enter member name: ");
            Member member = new Member(memberName);
            household.addMember(member);
            System.out.println("Member added: " + memberName);
        }
    }

    private static void addChores() {
        if (choreList == null) {
            choreList = new ChoreList();
        }
        int numChores = UserInputHandler.getIntInput("Enter the number of chores to add: ");
        for (int i = 0; i < numChores; i++) {
            String choreName = UserInputHandler.getStringInput("Enter chore name: ");
            DayOfWeek deadline = selectDayOfWeek();
            Chore chore = new Chore(choreName, deadline);
            choreList.addChore(chore);
            System.out.println("Chore added: " + choreName);
        }
    }

    private static DayOfWeek selectDayOfWeek() {
        System.out.println("Select deadline day of the week:");
        System.out.println("1. Monday");
        System.out.println("2. Tuesday");
        System.out.println("3. Wednesday");
        System.out.println("4. Thursday");
        System.out.println("5. Friday");
        System.out.println("6. Saturday");
        System.out.println("7. Sunday");
        int choice = getUserChoice(1, 7);
        return DayOfWeek.of(choice);
    }

    private static void assignChores() {
        if (household == null) {
            System.out.println("Please create a household first.");
            return;
        }
        if (choreList == null) {
            System.out.println("Please add chores first.");
            return;
        }
        if (schedule == null) {
            schedule = new Schedule();
        }
    
        // Ask the user to select a member
        Member selectedMember = selectMember();
        if (selectedMember == null) {
            System.out.println("No member selected. Returning to main menu.");
            return;
        }
    
        // Assign chores to the selected member
        assignChoresToMember(selectedMember);
    }
    
    private static Member selectMember() {
        List<Member> members = household.getMembers();
        if (members.isEmpty()) {
            System.out.println("No members in the household. Returning to main menu.");
            return null;
        }
    
        System.out.println("Select a member:");
        for (int i = 0; i < members.size(); i++) {
            System.out.println((i + 1) + ". " + members.get(i).getName());
        }
    
        int choice = getUserChoice(1, members.size()) - 1;
        return members.get(choice);
    }
    
    private static void assignChoresToMember(Member member) {
        boolean assignMoreChores = true;
        while (assignMoreChores) {
            choreList.displayChores();
    
            if (choreList.isEmpty()) {
                System.out.println("There are no more chores to be assigned.");
                break;
            }
    
            int choreIndex = UserInputHandler.getIntInput("Enter the index of the chore to assign (1-" + choreList.getChores().size() + "): ") - 1;
            if (choreIndex < 0 || choreIndex >= choreList.getChores().size()) {
                System.out.println("Invalid chore index.");
                continue;
            }
    
            Chore chore = choreList.getChores().get(choreIndex);
            schedule.assignChore(chore.getDeadline(), member, chore);
            System.out.println("Chore assigned: " + chore.getName() + " to " + member.getName() + " for " + chore.getDeadline());
    
            // Removes the assigned chore from the chore list
            choreList.removeChore(chore);
    
            String response = UserInputHandler.getStringInput("Do you want to assign another chore to " + member.getName() + "? (yes/no): ");
            if (!response.equalsIgnoreCase("yes")) {
                assignMoreChores = false;
            }
        }
    }
    

    private static void viewSchedule() {
        if (schedule == null || schedule.getScheduleMap().isEmpty()) {
            System.out.println("No schedule available. Please generate a schedule first.");
            return;
        }
        System.out.println("Current Schedule:");
        schedule.displaySchedule();
    }

    private static void generateSchedule() {
        if (schedule == null) {
            System.out.println("No chores assigned yet. Please assign chores first.");
            return;
        }
        String fileName = UserInputHandler.getStringInput("Enter file name to save schedule: ");
        FileManager.writeScheduleToFile(fileName, schedule);
    }
}