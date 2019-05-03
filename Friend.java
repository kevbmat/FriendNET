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

public class Friend {
    private String name;
    private int rank;

    public Friend(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public String toString() {
        return name + ": " + rank;
    }
}