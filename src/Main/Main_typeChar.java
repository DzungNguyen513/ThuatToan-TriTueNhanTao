package Main;
import java.util.List;
import java.util.Scanner;

import A_sao.A_sao;
import BFS_char.BFS_typeChar;

public class Main_typeChar {

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		int lc = 0;
		do {
			System.out.println("------------------MENU-------------------");
			System.out.println("|1. BFS theo đường đi từ 1 đỉnh cho trước|");
			System.out.println("|2. BFS tìm đường đi đến 1 đỉnh bất kì   |");
			System.out.println("|3. Best First Search                    |");
			System.out.println("|4. Thuật toán A*                        |");
			System.out.println("|5. Thoát !                              |");
			System.out.println("-----------------Group7-------------------");
			System.out.print("Nhập lựa chọn: ");  lc = sc.nextInt();
	        if(lc == 1) {
	        	System.out.print("Nhập đỉnh bạn muốn đi: ");
	        	char v = sc.next().charAt(0);
	    		BFS_typeChar b = new BFS_typeChar();
	    		b.input();
	        	b.BFS_TimDuong(v);
	        	System.out.println();
	        } else if(lc == 2) {
	        	System.out.print("Nhập đỉnh bạn muốn tìm: ");
	        	char v = sc.next().charAt(0);
	    		BFS_typeChar b = new BFS_typeChar();
	    		b.input();
	        	b.BFS_TimDinh(v);
	        	System.out.println();
	        } else if(lc == 3) {
	        	
	        } else if(lc == 4) {
	        	A_sao a_sao = new A_sao();
	            
	            // Thay đổi đường dẫn file tại đây
	            String filePath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\A_sao\\input.txt";
	            
	            a_sao.input(filePath);

	            String startNode = "A";
	            String goalNode = "B";

	            List<String> path = a_sao.astar(startNode, goalNode);

	            if (path == null) {
	                System.out.println("No path found from " + startNode + " to " + goalNode + ".");
	            } else {
	                System.out.println("Shortest path from " + startNode + " to " + goalNode + ": " + String.join(" -> ", path));
	            }
	        } else if(lc == 5) {
	        	break;
	        }
		}while (lc != 0);   
		sc.close();
	}
}
