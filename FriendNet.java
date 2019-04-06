import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class FriendNet {

    private Map<String, String> friendMap = new HashMap<>();
    public static void main(String[] args) {
        File file = new File("friends.txt");
        Scanner inputFile = new Scanner(file);
<<<<<<< HEAD
        //create arrayLists and vars
        ArrayList<String> person1 = new ArrayList<String>();
        ArrayList<String> person2 = new ArrayList<String>();
        ArrayList<Int> friendVal = new ArrayList<Int>();

        //start input
        while (inputFile.hasNext()){
            String personFrom = inputFile.next();
            String personTo = inputFile.next();
            Int val = inputFile.nextInt();

            person1.add(personFrom);
            person2.add(personTo);
            friendVal.add(val);
        }

        System.out.println(person1);



=======

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

>>>>>>> 6be59028d9f5385923c3a32ee8ac5d25a0a020a3
    }

}