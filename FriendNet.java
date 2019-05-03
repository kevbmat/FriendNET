// Kevin Mattappally
// Davis Fairchild
// Ryan Hays
	
// How we are going to implement the best freind chain
//  1. We will implement the best friend chain by utilizing the 
//      Dijkstra's algorith
// Here are our two killer features:
//  1. Finding Mutual Friends between two users
//      a. To find the mutual friends, we would be using
//         the brute force algorithm.
//  2. Finding the most popular / best friend
//      a. To find the most popular friend, we would find the friend
//         in the graph with the highest overall average friend rating.

import java.util.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.*;
import java.lang.*;

public class FriendNet {
    private static Map<String, List<Friend>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        File file = new File("Friends.txt");
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
            if (!map.containsKey(friend)) {
                map.put(friend, new ArrayList<>());
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
        System.out.println("\nWhat do you want to do?");
        System.out.println("1) Check if user exists");
        System.out.println("2) Check connection between users");
        System.out.println("3) Quit");
        System.out.println("4) Best Friend Chain");
        System.out.println("5) Find Mutual Friends");
        System.out.println("6) Find Best Quality Friend");
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

    public static void bestFriendChain() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the first user: ");
        String firstUser = sc.next();
        System.out.print("Enter the second user: ");
        String secondUser = sc.next();
        System.out.println("Now calculating the best friend chain:");
        // starting main portion of algorithm
        // based of Dikstra's algorithm
        // uses ArrayLists rather than PriorityQueue
        ArrayList<String> nodes = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<String> prevs = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        for (String u : map.keySet()) {
            nodes.add(u);
            prevs.add("_");
            visited.add(false);
            if (u.equals(firstUser)) {
                distances.add(0);
            } else {
                distances.add(Integer.MAX_VALUE);
            }
        }
        // initial contents of lists
        printLists("Initial", nodes, distances, prevs, visited);
        for (int i = 0; i < nodes.size(); i++) {
            // calculate min distance index
            int minIndex = 0;
            for (int j = 1; j < nodes.size(); j++) {
                if (distances.get(j) < distances.get(minIndex) && visited.get(j) == false) {
                    minIndex = j;
                }
            }
            visited.set(minIndex, true);
            // for each adjacent vertex calculate min distance
            int numFriends = map.get(nodes.get(minIndex)).size();
            for (int j = 0; j < numFriends; j++) {
                String currentFriend = map.get(nodes.get(minIndex)).get(j).getName();
                int unfriendlinessRank = 10 - map.get(nodes.get(minIndex)).get(j).getRank();
                // see which index that friend is located at
                for (int k = 0; k < nodes.size(); k++) {
                    if (nodes.get(k).equals(currentFriend)) {
                        // update current min distance
                        if (distances.get(k) > (unfriendlinessRank + distances.get(minIndex))) {
                            // found a faster route
                            distances.set(k, unfriendlinessRank + distances.get(minIndex));
                            prevs.set(k, nodes.get(minIndex));
                        }
                    }
                }
            }
        }
        // final contents of lists
        printLists("Final", nodes, distances, prevs, visited);
        // showing the user the total unfriendliness value
        int unfriendlinestValue = distances.get(nodes.indexOf(secondUser));
        // show best friend chain
        if (unfriendlinestValue >= 2000000 || unfriendlinestValue <= -2000000) {
            System.out.println("\nUnable to Obtain Friend Chain :(");
        } else {
            // friend chain is possible
            System.out.printf("\nTotal Unfriendlinest Value = %d\n", unfriendlinestValue);
            // representing the chain in an arraylist
            ArrayList<String> chain = new ArrayList<>();
            chain.add(nodes.get(distances.indexOf(unfriendlinestValue)));
            while (!(prevs.get(nodes.indexOf(chain.get(0))).equals("_"))) {
                chain.add(0, prevs.get(nodes.indexOf(chain.get(0))));
            }
            // printing out the best friend chain
            System.out.print("Best Friend Chain: ");
            System.out.print(chain.get(0));
            for (int i = 1; i < chain.size(); i++) {
                System.out.print(" --> " + chain.get(i));
            }
            System.out.println();
        }
    }

    // prints out the lists in a pretty fashion
    public static void printLists(String time, ArrayList a, ArrayList b, ArrayList c, ArrayList d) {
        System.out.println("\n" + time + " list contents");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    public static void mutualFriends() {
        //get user1's friends, check user 2's friends, find commonalities
        ArrayList<String> mutualFriends = new ArrayList<String>();
        
        //establish people to search between
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the first user: ");
            String firstUser = sc.next();
        System.out.print("Enter the second user: ");
            String secondUser = sc.next();
        
        //search through both friends lists getting common ones
        if (map.containsKey(firstUser)) {
            if (map.containsKey(secondUser)) {
                List<Friend> firstUserFriends = map.get(firstUser); //get user1 friends
                List<Friend> secondUserFriends = map.get(secondUser); //get user2 friends
                for (int i = 0; i < firstUserFriends.size(); i++) {
                    for (int j = 0; j < secondUserFriends.size(); j++){
                        if (firstUserFriends.get(i).getName().equals(secondUserFriends.get(j).getName())) { //check if equal
                            String tempString = firstUserFriends.get(i).getName(); //if equal make a tempString
                            mutualFriends.add(tempString);                                              //add that tempString to an array list
                        }
                    }
                }

                if (!mutualFriends.isEmpty()){
                    System.out.println("These Mutual Friends were found:"); //print array list
                    System.out.println(mutualFriends);
                }

                else {
                    System.out.println("No mutual friends were found."); //no mutual friends found
                }
            }
            else {
                System.out.println(secondUser + " is not in this network" + "\n");
            }
        } else {
            System.out.println(firstUser + " is not in this network" + "\n");
        }
    }

    public static void bestQualityFriend() {

        ArrayList<String> friendsList = new ArrayList<String>();
        ArrayList<String> usersList = new ArrayList<String>();
        ArrayList<String> maxFriendshipList = new ArrayList<String>();
        int friendCounter = 0;
        int runningFriendshipTotal = 0;
        double averageFriendshipLevel = 0;
        double valueOfBestFriend = 0;
        int indexOfBestFriend = -1;


        System.out.println("");
        System.out.println("Users: Friends and Ranks");
        for (String keys : map.keySet())
        {

            System.out.println(keys + ":"+ map.get(keys));
        }
        //System.out.println("\n");
        //System.out.println(map.keySet());
        for (String keys : map.keySet())
        {
            String tempString = map.get(keys).toString();
            //System.out.println(tempString);
            //tempString = tempString.replaceAll("\\D", "");
            friendsList.add(tempString);
            usersList.add(keys);
        }

        //loop iterates over every key in the map (users with associate friend lists)
        for (int i = 0; i < usersList.size(); i++)
        {
            String currentUser = usersList.get(i);
            //System.out.println(currentUser);
            friendCounter = 0;
            runningFriendshipTotal = 0;
            averageFriendshipLevel = 0;

            //iterates over the vlaues in the map (friends list with ranks)
            for (int j = 0; j < friendsList.size(); j++)
            {
                //if index i is the same as index j, this is the user's own list of friends
                //exclude the list of the user's own friends
                if (j != i)
                {
                    //iterates over each character of the values (friends and ranks)
                    //System.out.println(friendsList.get(j));
                    boolean isFriend = friendsList.get(j).indexOf(currentUser) != -1? true: false;
                    //System.out.println(isFriend);
                    if (isFriend)
                    {
                        int firstIndexOfName = friendsList.get(j).indexOf(currentUser);
                        int lengthOfName = currentUser.length();
                        friendCounter++;

                        //System.out.println(firstIndexOfName);
                        //System.out.println("Name lenght: " + lengthOfName);
                        int friendshipRank = Character.getNumericValue(friendsList.get(j).charAt(firstIndexOfName + lengthOfName + 2));
                        //System.out.println(friendshipRank);
                        //System.out.print("Friendshiplevel: ");
                        if (friendshipRank == 1)
                        {
                            if(friendsList.get(j).charAt(firstIndexOfName + lengthOfName + 3) == '0')
                            {
                              friendshipRank = 10;
                              //System.out.println(friendshipRank);
                            }
                            else
                            {
                              friendshipRank = 1;
                              //System.out.println(friendshipRank);
                            }
                        }
                        else
                        {

                            //System.out.println(friendshipRank);
                        }

                        //System.out.println(friendsList.get(j).charAt(firstIndexOfName + lengthOfName + 2));
                        //System.out.print(friendsList.get(j).charAt(firstIndexOfName + lengthOfName + 3));
                        runningFriendshipTotal += friendshipRank;

                    }

                }
            }
            //System.out.println("Number of friends: " + friendCounter);
            //System.out.println("Total friendshiplevel: " + runningFriendshipTotal);
            averageFriendshipLevel = runningFriendshipTotal / (double) friendCounter;
            //System.out.println("Average friendship total: " + averageFriendshipLevel);
            if (averageFriendshipLevel > valueOfBestFriend)
            {
              indexOfBestFriend = i;
              valueOfBestFriend = averageFriendshipLevel;
            }
        }
        System.out.println("");
        //System.out.println("The index of the best quality friend is: " + indexOfBestFriend);
        System.out.println("The user who is the best quality friend is " + usersList.get(indexOfBestFriend) + " with a level of: " + valueOfBestFriend);

        // System.out.println(usersList.get(0));
        // System.out.println(friendsList.get(0));
        // System.out.println(friendsList.get(0).charAt(0));
        // System.out.println(friendsList.get(0).length());

    }
}

