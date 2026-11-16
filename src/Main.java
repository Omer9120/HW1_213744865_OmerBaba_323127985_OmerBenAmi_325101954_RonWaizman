import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Message> messages = new ArrayList<>();
        
        messages.add(new BoardMessage("Alpha Command", "All units return to base", 3, PriorityType.URGENT, "All Teams"));
        messages.add(new BoardMessage("HQ", "Weather update: heavy rain expected", 1, PriorityType.REGULAR, "Support Team"));
        
        messages.add(new EmailMessage("Intel Officer", "Satellite imagery attached", 4, "Target Area Scans"));
        messages.add(new EmailMessage("Logistics", "Supply drop coordinates", 2, "Daily Supplies"));
        
        try {
            messages.add(new RadioMessage("Bravo Leader", "Engaging target", 3, 105.5));
            messages.add(new RadioMessage("Charlie Scout", "Sector clear", 2, 88.0));
        } catch (FrequencyOutOfRangeException e) {
            System.out.println("Error initializing hardcoded radio messages: " + e.getMessage());
        }

        int choice = -1;
        System.out.println("Welcome to the Tactical Communication System");

        while (choice != 0) {
            System.out.println("\n--- Command Post Menu ---");
            System.out.println("1. Add Message (Board / Radio / Email)");
            System.out.println("2. Delete Message by Index");
            System.out.println("3. Print all Messages");
            System.out.println("4. Count Messages by Word (Search)");
            System.out.println("5. Print Digital Messages Only");
            System.out.println("6. Update Radio Frequency (Custom Option)"); // סעיף vi
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: 
                    System.out.println("Select Message Type: 1-Board, 2-Radio, 3-Email");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Sender: "); String sender = scanner.nextLine();
                    System.out.print("Content: "); String content = scanner.nextLine();
                    System.out.print("Classification Level: "); int cLevel = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 1) {
                        System.out.print("Team Name: "); String team = scanner.nextLine();
                        messages.add(new BoardMessage(sender, content, cLevel, PriorityType.REGULAR, team));
                        System.out.println(">> Board Message added.");
                    } else if (type == 2) {
                        System.out.print("Frequency: "); double freq = scanner.nextDouble();
                        scanner.nextLine();
                        try {
                            messages.add(new RadioMessage(sender, content, cLevel, freq));
                            System.out.println(">> Radio Message added.");
                        } catch (FrequencyOutOfRangeException e) {
                            System.out.println(">> " + e.getMessage());
                        }
                    } else if (type == 3) {
                        System.out.print("Subject: "); String subject = scanner.nextLine();
                        messages.add(new EmailMessage(sender, content, cLevel, subject));
                        System.out.println(">> Email Message added.");
                    }
                    break;
                    
                case 2: 
                    System.out.print("Enter index to delete (0 to " + (messages.size() - 1) + "): ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index < messages.size()) {
                        messages.remove(index);
                        System.out.println(">> Message deleted.");
                    } else {
                        System.out.println(">> Invalid index.");
                    }
                    break;
                    
                case 3:
                    System.out.println("\n--- All Messages ---");
                    for (int i = 0; i < messages.size(); i++) {
                        System.out.println("Index " + i + ":\n" + messages.get(i).toString() + "\n");
                    }
                    break;
                    
                case 4: 
                    System.out.print("Enter word to search: ");
                    String searchWord = scanner.nextLine();
                    ArrayList<String> searchList = new ArrayList<>();
                    searchList.add(searchWord);
                    int matchCount = 0;
                    
                    for (Message m : messages) {
                        if (m.find(searchList)) {
                            matchCount++;
                        }
                    }
                    System.out.println(">> Found " + matchCount + " message(s) containing the word '" + searchWord + "'.");
                    break;
                    
                case 5: 
                    System.out.println("\n--- Digital Messages ---");
                    for (Message m : messages) {
                        if (m instanceof IDigital) {
                            System.out.println(m.generatePreview());
                            System.out.println(((IDigital) m).printCommunicationMethod() + "\n");
                        }
                    }
                    break;

                case 6: 
                    System.out.print("Enter index of Radio Message to update: ");
                    int rIndex = scanner.nextInt();
                    if (rIndex >= 0 && rIndex < messages.size() && messages.get(rIndex) instanceof RadioMessage) {
                        RadioMessage rm = (RadioMessage) messages.get(rIndex);
                        System.out.print("Current Frequency is " + rm.getFrequency() + ". Enter new frequency: ");
                        double newFreq = scanner.nextDouble();
                        try {
                            rm.setFrequency(newFreq);
                            System.out.println(">> Frequency updated successfully.");
                        } catch (FrequencyOutOfRangeException e) {
                            System.out.println(">> Update failed: " + e.getMessage());
                        }
                    } else {
                        System.out.println(">> Invalid index or message is not a Radio Message.");
                    }
                    break;
                    
                case 0:
                    System.out.println("Shutting down. Goodbye!");
                    break;
                    
                default:
                    System.out.println(">> Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}