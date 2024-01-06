package BFS;
/*
10 11
1 2
1 3 
1 10
2 4 
3 5
3 6 
5 8
5 10 
6 7
7 3 
8 9

9 8
A B
A C
A D
B K
C E
D F
D G
G H

*/

public class Main {
	public static void main(String[] args) {
        BFS_AI b = new BFS_AI();
        b.input();
        b.bfs(1);
    }
}
