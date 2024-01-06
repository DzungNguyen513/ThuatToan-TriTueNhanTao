package BFS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_AI {
	public int n;
	public ArrayList<Integer>[] adj;
    public boolean[] visited;
    
    public void input() {
    	Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // Số đỉnh
        int m = sc.nextInt(); // Số cạnh
        adj = new ArrayList[n + 1];
        visited = new boolean[n + 1]; // Khởi tạo mảng visited
        
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y);
            // adj[y].add(x);
        }
        Arrays.fill(visited, false);
        sc.close();
    }
    
    public void bfs(int u) {
        Queue<Integer> q = new LinkedList<>();
        q.add(u);
        visited[u] = true;
        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");
            for (int x : adj[v]) {
                if (!visited[x]) {
                    q.add(x);
                    visited[x] = true;
                }
            }
        }
    }
}
