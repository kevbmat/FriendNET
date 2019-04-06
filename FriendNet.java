import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class FriendNet {
    public static void main(String[] args) throws IOException {
        File file = new File("friends.txt");
        Scanner inputFile = new Scanner(file);
        //create arrayLists and vars
        ArrayList<String> users = new ArrayList<String>();
        ArrayList<String> friends = new ArrayList<String>();
        ArrayList<Integer> ranks = new ArrayList<Integer>();
        //start input
        while (inputFile.hasNext()){
            String user = inputFile.next();
            String friend = inputFile.next();
            int rank = inputFile.nextInt();
            users.add(user);
            friends.add(friend);
            ranks.add(rank);
        }
        System.out.println(users);
        System.out.println(friends);
        System.out.println(ranks);
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
                showMenu();
        }

    }

}