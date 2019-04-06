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