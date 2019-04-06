import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class FriendNet {

    private Map<String, String> friendMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        File file = new File("friends.txt");
        Scanner inputFile = new Scanner(file);
        //create arrayLists and vars
        ArrayList<String> person1 = new ArrayList<String>();
        ArrayList<String> person2 = new ArrayList<String>();
        ArrayList<Integer> friendVal = new ArrayList<Integer>();

        //start input
        while (inputFile.hasNext()){
            String personFrom = inputFile.next();
            String personTo = inputFile.next();
            int val = inputFile.nextInt();

            person1.add(personFrom);
            person2.add(personTo);
            friendVal.add(val);
        }
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(friendVal);
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