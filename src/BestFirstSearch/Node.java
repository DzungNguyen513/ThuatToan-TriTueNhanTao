package BestFirstSearch;

public class Node {
	char tenDinh;
    int cost;
    int heuristic; 
    public Node(char tenDinh, int chiPhiDinh, int kt) {
        this.tenDinh = tenDinh;
        this.cost = chiPhiDinh;
        this.heuristic = kt;
    }
}
