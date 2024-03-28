package BestFirstSearch;

public class Node implements Comparable<Node> {
    public char dinh;
    public int heuristic;

    public Node(char dinh, int heuristic) {
        this.dinh = dinh;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.heuristic, other.heuristic);
    }
}