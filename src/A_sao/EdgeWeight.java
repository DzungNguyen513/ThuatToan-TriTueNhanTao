package A_sao;

public class EdgeWeight {
    char dinhDen;
    int cost; 

    public EdgeWeight(char dinhDen, int cost) {
        this.dinhDen = dinhDen;
        this.cost = cost;
    }
    @Override
    public String toString() {
        return "(" + dinhDen + ", " + cost + ")";
    }
}
