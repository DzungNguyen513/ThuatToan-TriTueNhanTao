package BFS_char;

public class MainBFS {
	 public static void main(String[] args) {
	        BFS_typeChar bfsGraph = new BFS_typeChar();
	        bfsGraph.input(); // Đọc đồ thị từ tệp input2.txt

	        System.out.println("BFS starting from vertex 'A':");
	        bfsGraph.BFS_TimDuong('A'); // Thực hiện BFS từ đỉnh A

	        System.out.println("\n\nBFS searching for path to vertex 'E':");
	        bfsGraph.BFS_TimDinh('H'); // Thực hiện BFS để tìm đường đến đỉnh E
	    }
}
