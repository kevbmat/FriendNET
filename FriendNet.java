import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class FriendNet {
    private static Map<String, List<Friend>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        File file = new File("friends.txt");
        Scanner inputFile = new Scanner(file);
        while (inputFile.hasNext()){
            String user = inputFile.next();
            String friend = inputFile.next();
            int rank = inputFile.nextInt();
            Friend f = new Friend(friend, rank);
            if (map.containsKey(user)) {
                List<Friend> temp = map.get(user);
                temp.add(f);
                map.put(user, temp);
            } else {
                List<Friend> newFriend = new ArrayList<>();
                newFriend.add(f);
                map.put(user, newFriend);
            }
        }
        inputFile.close();
        do {
            char choice = showMenu();
        } while (true);
    }

    public static char showMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1) Check if user exists");
        System.out.println("2) Check connection between users");
        System.out.println("3) Quit");
        char choice = sc.next().charAt(0);
        switch (choice) {
            case '1':
                checkUserExists();
                // Check if user exists
                break;
            case '2':
                // Check connection between users
                break;
            case '3':
                // Quit
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice");
        }
        return choice;
    }

    public static void checkUserExists() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter a user to check: ");
        String enteredUser = sc.next();
        if (map.containsKey(enteredUser)) {
            System.out.println(enteredUser + " is in this network");
        } else {
            System.out.println(enteredUser + " is not in this network");
        }
    }

    public static void checkConnection(String user1, String user2) {

    }
}