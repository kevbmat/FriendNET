
public class FriendNet {

    private Map<String, String> friendMap = new HashMap<>();
    public static void main(String[] args) {
        File file = new File("Friends.txt");
        Scanner inputFile = new Scanner(file);

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