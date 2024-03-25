package BestFirstSearch;

public class Edge {
	char dinhDen;
    int cost;

    public Edge(char dinhDen, int chiPhiCanh) {
        this.dinhDen = dinhDen;
        this.cost = chiPhiCanh;
    }
}
