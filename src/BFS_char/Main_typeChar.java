package BFS_char;

import java.util.Scanner;

import BFS.BFS_AI;

public class Main_typeChar {

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		BFS_typeChar b = new BFS_typeChar();
		b.input();
		int lc = 0;
		do {
			System.out.println("------------------MENU-------------------");
			System.out.println("|1. BFS theo đường đi từ 1 đỉnh cho trước|");
			System.out.println("|2. BFS tìm đường đi đến 1 đỉnh bất kì   |");
			System.out.println("|3. Thoát !                              |");
			System.out.println("-----------------------------------------");
			System.out.print("Nhập lựa chọn: ");  lc = sc.nextInt();
	        if(lc == 1) {
	        	System.out.print("Nhập đỉnh bạn muốn đi: ");
	        	char v = sc.next().charAt(0);
	        	b.BFS_TimDuong(v);
	        	System.out.println();
	        } else if(lc == 2) {
	        	System.out.print("Nhập đỉnh bạn muốn tìm: ");
	        	char v = sc.next().charAt(0);
	        	b.BFS_TimDinh(v);
	        	System.out.println();
	        } else if(lc == 3) {
	        	break;
	        }
		}while (lc != 0);   
		sc.close();

	}

}
