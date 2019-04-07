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
        System.out.println(map);
        inputFile.close();
        do {
            showMenu();
        } while (true);
    }

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1) Check if user exists");
        System.out.println("2) Check connection between users");
        System.out.println("3) Quit");
        char choice = sc.next().charAt(0);
        switch (choice) {
            case '1':
                checkUserExists();
                break;
            case '2':
                checkConnection();
                break;
            case '3':
                // Quit
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }

    public static void checkUserExists() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter a user to check: ");
        String enteredUser = sc.next();
        if (map.containsKey(enteredUser)) {
            System.out.println(enteredUser + " is in this network\n");
        } else {
            System.out.println(enteredUser + " is not in this network\n");
        }
    }

    public static void checkConnection() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the first user: ");
        String firstUser = sc.next();
        System.out.print("Enter the second user: ");
        String secondUser = sc.next();
        if (map.containsKey(firstUser)) {
            List<Friend> firstUserFriends = map.get(firstUser);
            for (int i = 0; i < firstUserFriends.size(); i++) {
                if (firstUserFriends.get(i).getName().equals(secondUser)) {
                    System.out.println("The weight of " + firstUser + " to " + secondUser + " = " + firstUserFriends.get(i).getRank());
                    return;
                }
            }
            System.out.println(secondUser + " is not a friend of " + firstUser + "\n");
        } else {
            System.out.println(firstUser + " is not in this network" + "\n");
        }
    }
}