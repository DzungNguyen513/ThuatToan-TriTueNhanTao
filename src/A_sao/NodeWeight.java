package A_sao;

import java.nio.charset.Charset;

public class NodeWeight{
    char tenDinh;
    int gCost; 
    int hCost; 
    int fCost;

    public NodeWeight(char tenDinh, int gCost, int hCost, int fCost) {
        this.tenDinh = tenDinh;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = fCost;
    }
    @Override
    public String toString() {
        return tenDinh + "-" + fCost;
    }
}
