package BestFirstSearch;

public class Node {
	char tenDinh;
    int chiPhiDinh;
    int kt; //Đây là ước lượng heuristic về chi phí để đi từ nút hiện tại đến nút đích. 
    			  //Heuristic này giúp hàng đợi ưu tiên quyết định thứ tự ưu tiên của các nút.
    
    public Node(char tenDinh, int chiPhiDinh, int kt) {
        this.tenDinh = tenDinh;
        this.chiPhiDinh = chiPhiDinh;
        this.kt = kt;
    }
}
