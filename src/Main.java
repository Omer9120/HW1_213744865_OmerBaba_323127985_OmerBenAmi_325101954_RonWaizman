import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Message> messages = new ArrayList<>();
        int choice = -1;

        System.out.println("Welcome to the Tactical Communication System");

        while (choice != 0) {
            System.out.println("\n--- Command Post Menu ---");
            System.out.println("1. Add Board Message");
            System.out.println("2. Add Radio Message");
            System.out.println("3. Delete Message by Index");
            System.out.println("4. Search Messages by Word");
            System.out.println("5. Print all Radio Messages");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Sender: "); String bSender = scanner.nextLine();
                    System.out.print("Content: "); String bContent = scanner.nextLine();
                    System.out.print("Classification Level (int): "); int bClass = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Team Name: "); String bTeam = scanner.nextLine();
                    messages.add(new BoardMessage(bSender, bContent, bClass, PriorityType.URGENT, bTeam));
                    System.out.println(">> Board Message added successfully.");
                    break;
                    
                case 2:
                    System.out.print("Sender: "); String rSender = scanner.nextLine();
                    System.out.print("Content: "); String rContent = scanner.nextLine();
                    System.out.print("Classification Level (int): "); int rClass = scanner.nextInt();
                    System.out.print("Frequency (30.0 - 512.0): "); double rFreq = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        messages.add(new RadioMessage(rSender, rContent, rClass, rFreq));
                        System.out.println(">> Radio Message added successfully.");
                    } catch (FrequencyOutOfRangeException e) {
                        System.out.println(">> " + e.getMessage());
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter index to delete (0 to " + (messages.size() - 1) + "): ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index < messages.size()) {
                        messages.remove(index);
                        System.out.println(">> Message deleted.");
                    } else {
                        System.out.println(">> Invalid index.");
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter word to search: ");
                    String word = scanner.nextLine();
                    ArrayList<String> searchWords = new ArrayList<>();
                    searchWords.add(word);
                    boolean found = false;
                    for (Message m : messages) {
                        if (m.find(searchWords)) {
                            System.out.println("\nFound in:\n" + m.toString());
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println(">> Word not found in any message.");
                    }
                    break;
                    
                case 5:
                    System.out.println("\n--- Radio Messages ---");
                    for (Message m : messages) {
                        if (m instanceof RadioMessage) {
                            System.out.println(m.generatePreview());
                        }
                    }
                    break;
                    
                case 0:
                    System.out.println("Shutting down Command Post. Goodbye!");
                    break;
                    
                default:
                    System.out.println(">> Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}