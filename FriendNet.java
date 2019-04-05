import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class FriendNet {

    private Map<String, String> friendMap = new HashMap<>();
    public static void main(String[] args) {
        File file = new File("friends.txt");
        Scanner inputFile = new Scanner(file);
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



    }

}