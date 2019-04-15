// Kevin Mattappally
// Davis Fairchild
// Ryan Hays

// How we are going to implement the best freind chain
//  1. We will implement the best friend chain by utilizing the 
//      branch and bound method. Specifically we will be using Dijkstra's algorithm
//      For this, we would find the lower bound
//      (in terms of the path) for each node in the tree, just like we did
//      for the assignment problem. This would use finding the lower bound 
//      unfriendlinest ranking, then branching based on the lower bound (just like shown in class)
//      Here are our two killer features:
//  1. Finding Mutual Friends between two users
//      a. To find the mutual friends, we would be using
//         the brute force algorithm (or sorting then binary search)
//  2. Finding the most popular / best friend
//      a. To find the most popular friend, we would have to iterate through
//      each friend's list and have a count for each friend. At the end, we take 
//      the friend with the highest count. We can also find the friend with the highest
//      average friend rank, by taking the addition of the ranks and then dividing by the count.
//  Honorable Mention Ideas:
//      a. We would be adding a gender attribute to each user
//         then we could sort the user's by what gender they are.
//      b. We can also add a relationship status to the user
//      c. Both of these ideas would result in making a seperate User object.

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
        System.out.println("4) Best Friend Chain");
        System.out.println("5) Find Mutual Friends");
        System.out.println("6) Find Best Quality Methods");
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
            case '4':
                bestFriendChain();
                break;
            case '5':
                mutualFriends();
                break;
            case '6':
                bestQualityFriend();
                break;
            default:
                System.out.println("Invalid Choice");
        }
        sc.close();
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
        sc.close();
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
        sc.close();
    }

    public static void bestFriendChain() {

    }

    public static void mutualFriends() {
        
    }

    public static void bestQualityFriend() {
        
    }
}