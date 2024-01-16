package Main;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import A_sao.A_sao;
import BFS_char.BFS_typeChar;
import BestFirstSearch.BestFirstSearch;

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
	        	BestFirstSearch bfs = new BestFirstSearch();
	            try {
	                bfs.input();	             
	                System.out.print("Nhập đỉnh bạn muốn tìm: ");
	                char goal = sc.next().charAt(0);
	                bfs.bestFirstSearch('A', goal);
	            } finally {
	                
	            }
	        	
	        } else if(lc == 4) {
	        	
	        } else if(lc == 5) {
	        	break;
	        }
		}while (lc != 0);   
		sc.close();
	}
}
